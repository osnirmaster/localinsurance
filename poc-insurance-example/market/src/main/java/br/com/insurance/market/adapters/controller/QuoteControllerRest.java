package br.com.insurance.market.adapters.controller;

import br.com.insurance.market.adapters.dto.RequestQuote;
import br.com.insurance.market.adapters.dto.ResponseQuote;
import br.com.insurance.market.domain.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/insurance/quote")
public class QuoteControllerRest {

    @PostMapping
    public ResponseEntity<ResponseQuote> price(@RequestBody RequestQuote request, UriComponentsBuilder uriBuilder){
        Quote quote =

        URI uri = uriBuilder.path("/quote/{id}").buildAndExpand(productSaved.getProductId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(productSaved))
    }
}
