package br.com.insurance.market.adapters.controller;

import br.com.insurance.market.adapters.dto.RequestQuote;
import br.com.insurance.market.adapters.dto.UpdateQuote;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.domain.usecase.GetQuoteCalculation;
import br.com.insurance.market.domain.usecase.GetQuoteFinalized;
import br.com.insurance.market.domain.usecase.UpdateParcelsQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/insurance/quote")
public class QuoteControllerRest {

    @Autowired
    private  GetQuoteCalculation getQuoteCalculation;
    @Autowired
    private UpdateParcelsQuote updateParcelsQuote;
    @Autowired
    private GetQuoteFinalized quoteFinalized;

    @PostMapping
    public ResponseEntity price(@RequestBody RequestQuote request, UriComponentsBuilder uriBuilder) throws ExecutionException, InterruptedException {

        Quote quote = getQuoteCalculation.insuranceQuote(request.convertTo());
        URI uri = uriBuilder
                .path("/quote/{id}/customerId/{id}")
                .buildAndExpand(quote.getQuoteId(), quote.getCustomerId())
                .toUri();
        return ResponseEntity.created(uri).body(quote);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote( @PathVariable Long id ,@RequestBody @Valid UpdateQuote request){
/*        log.info("ID recebido: {}", id);
        log.info("request recebido: {}", request);*/
        Quote quote = updateParcelsQuote.includeParcel(request.convertTo());
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/{quoteId}/customer/{customerId}")
        public ResponseEntity<Quote> getQuote( @PathVariable String quoteId, @PathVariable String customerId){
        QuoteId key = new QuoteId();
        key.setCustomerId(customerId);
        key.setQuoteId(quoteId);

        Optional<Quote> quote = quoteFinalized.getQuote(key);
        return quote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
