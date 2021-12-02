package br.com.insurance.market.adapters.controller;

import br.com.insurance.market.adapters.dto.RequestQuote;
import br.com.insurance.market.adapters.dto.ResponseQuote;
import br.com.insurance.market.adapters.dto.UpdateQuote;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.usecase.GetQuoteCalculation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/insurance/quote")
public class QuoteControllerRest {

    @Autowired
    private  GetQuoteCalculation getQuoteCalculation;

    @PostMapping
    public ResponseEntity price(@RequestBody RequestQuote request, UriComponentsBuilder uriBuilder) throws ExecutionException, InterruptedException {

        Quote quote = getQuoteCalculation.insuranceQuote(request);
        URI uri = uriBuilder.path("/quote/{id}").buildAndExpand(quote.getQuoteId()).toUri();
        return ResponseEntity.created(uri).body(uri);

    }

    @PutMapping("/{quoteId}")
    public ResponseEntity updateQuote(@RequestBody UpdateQuote request){

        log.info("Calculo recebido: {}", request);
        return ResponseEntity.ok().body("OKAY");
    }
}
