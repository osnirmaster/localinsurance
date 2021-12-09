package br.com.insurance.reactive.quote.service;

import br.com.insurance.reactive.quote.model.Quote;
import br.com.insurance.reactive.quote.model.TermFeeTax;
import br.com.insurance.reactive.quote.repository.TermFeeTaxRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class TermFeeService {

    private final TermFeeTaxRepository termFeeTaxRepository;

    public TermFeeService(TermFeeTaxRepository termFeeTaxRepository) {
        this.termFeeTaxRepository = termFeeTaxRepository;
    }

    public Mono<TermFeeTax> getTax(String productCode, Integer timeDays ){
        CompletableFuture<TermFeeTax> tax = termFeeTaxRepository.getTermFeeByID(productCode, timeDays)
                .whenComplete((ta, ex) -> {
                    if (null == ta) {
                        throw new IllegalArgumentException("Invalid tax id");
                    }
                })
                .exceptionally(ex -> new TermFeeTax());
        return Mono.fromFuture(tax);
    }
}
