package br.com.insurance.reactive.quote.controller;

import br.com.insurance.reactive.quote.model.Quote;
import br.com.insurance.reactive.quote.service.QuoteService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class Handler {

    private final QuoteService quoteService;

    public Handler(QuoteService quoteService) {
        this.quoteService = quoteService;
    }


    public Mono<ServerResponse> createQuote(ServerRequest request){

        final Flux<Quote> quote = request.bodyToFlux(Quote.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(quote.flatMap(quoteService::createGuote), Quote.class));

    }

    public Mono<ServerResponse> getQuote(ServerRequest request){
        String customerId = request.pathVariable("customerId");
        String quoteId = request.pathVariable("quoteId");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(quoteService.getQuote(customerId, quoteId), Quote.class);
    }

}
