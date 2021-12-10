package br.com.insurance.reactive.quote.service;

import br.com.insurance.reactive.quote.model.*;
import br.com.insurance.reactive.quote.repository.QuoteRepository;
import br.com.insurance.reactive.quote.repository.TermFeeTaxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@Slf4j
@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final TermFeeTaxRepository taxRepository;
    private final TermFeeService termFeeService;
    public Consumer<List<CreditContract>> consumer;

    public QuoteService(QuoteRepository quoteRepository, TermFeeTaxRepository taxRepository, TermFeeService termFeeService) {
        this.quoteRepository = quoteRepository;
        this.taxRepository = taxRepository;
        this.termFeeService = termFeeService;
    }

    public Flux<Quote> createGuote(Quote quote){

        return Flux.fromIterable(quote
                .getCreditContracts())
                .parallel()
                .runOn(Schedulers.parallel())
                .map( s -> toCalculate(quote, s)).log()
                .sequential()
                .doOnComplete(()->quoteRepository.save(quote)
                        .handle((quo, ex) -> ex == null ? quote : null)
                        .whenComplete((cus, ex) -> {
                            if (null == cus) {
                                throw new IllegalArgumentException("Invalid customerId");
                            }

                        })
                        .exceptionally(ex -> new Quote()))
                .thenMany(Flux.just(quote));

    }

    public Mono<Quote> getQuote(String customerId, String quoteId ){
        CompletableFuture<Quote> customer = quoteRepository.getQuoteByID(customerId, quoteId)
                .whenComplete((cus, ex) -> {
                    if (null == cus) {
                        throw new IllegalArgumentException("Invalid customerId");
                    }
                })
                .exceptionally(ex -> new Quote());
        return Mono.fromFuture(customer);
    }

    public Flux<CreditContract> createNumberSequence() {
        return Flux.create(sink -> this.consumer = items -> items.forEach(sink::next));
    }

    public Mono<TermFeeTax> getTax(String productCode, Integer ammountParcels){

        return termFeeService.getTax(productCode, ammountParcels);
    }

    public Flux<CreditContractParcel> toCalculate (Quote quote,CreditContract s){
        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i <= s.getCreditParcelAmount() ; i++){
            CompletableFuture<TermFeeTax> tax = taxRepository
                    .getTermFeeByID(quote.getproductCode(),
                            s.getCreditParcelAmount());
            try {
                BigDecimal priceTaxFee = s.getCreditPriceTotal().multiply(BigDecimal.valueOf(tax.get().tax));
                BigDecimal priceCoverTax = s
                        .getCreditPriceTotal()
                        .multiply(BigDecimal.valueOf(0.05));
                parcels.add(new Parcel(s
                        .getCreditParcelAmount(),
                        priceCoverTax.add(priceTaxFee)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        log.info("parcelas: {}",parcels);
        quote.getCreditContractParcel().add(new CreditContractParcel(s.getCreditAgreementId(), parcels));
        return Flux.just(new CreditContractParcel(s.getCreditAgreementId(), parcels)) ;
    }
}
