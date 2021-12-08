package br.com.insurance.reactive.quote.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(Handler handler){
        return RouterFunctions
                .route(GET("/offer/customer/{customerId}/quote/{quoteId}").and(accept(MediaType.APPLICATION_JSON)), handler::getQuote)
                .andRoute(POST("/offer/customer/{customerId}/quote/").and(accept(MediaType.APPLICATION_JSON)), handler::createQuote);

    }
}
