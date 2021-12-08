package br.com.insurance.reactive.quote.service;

import br.com.insurance.reactive.quote.model.Quote;
import br.com.insurance.reactive.quote.repository.QuoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Mono<Quote> createGuote(Quote quote){

        Quote quoteResponse = quoteRepository.save(quote)
                .handle((quo, ex) -> ex != null ? quote : null).join();

        return Mono.just(quote);
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
}
