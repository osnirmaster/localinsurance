package br.com.insurance.market.adapters.controller;

import br.com.insurance.market.adapters.dto.RequestQuote;
import br.com.insurance.market.adapters.dto.ResponseQuote;
import br.com.insurance.market.adapters.dto.UpdateQuote;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.usecase.GetQuoteCalculation;
import br.com.insurance.market.domain.usecase.UpdateParcelsQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/insurance/quote")
public class QuoteControllerRest {

    @Autowired
    private  GetQuoteCalculation getQuoteCalculation;
    @Autowired
    private UpdateParcelsQuote updateParcelsQuote;

    @PostMapping
    public ResponseEntity price(@RequestBody RequestQuote request, UriComponentsBuilder uriBuilder) throws ExecutionException, InterruptedException {

        Quote quote = getQuoteCalculation.insuranceQuote(request.convertTo());
        URI uri = uriBuilder.path("/quote/{id}").buildAndExpand(quote.getQuoteId()).toUri();
        return ResponseEntity.created(uri).body(uri);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote( @PathVariable Long id ,@RequestBody @Valid UpdateQuote request){
        log.info("ID recebido: {}", id);
        log.info("request recebido: {}", request);
        Quote quote = updateParcelsQuote.includeParcel(request.convertTo());
        return ResponseEntity.ok(quote);
    }
}
