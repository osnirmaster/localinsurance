package br.com.app.mobile.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/command")
public class CommandController {

    private final RestTemplate restTemplate;

    public CommandController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity command(@RequestBody RequestQuote request) {

        log.info("Solicitando cotacao...");
        ResponseCommandQuote response = restTemplate
                .postForObject("http://localhost:8099/insurance/quote/", request, ResponseCommandQuote.class);

        log.info("Iniciando  Polling da cotacao...");
        Quote quote = new Quote();
        while (!quote.getStatus().equals(QuoteStatus.FINISHED)) {

            log.info("Fazendo Polling ->");
            quote = restTemplate
                    .getForObject("http://localhost:8099/insurance/quote/" + response.getQuoteId() + "/customer/" + response.getCustomerId(),
                            Quote.class);
            log.info("Response -> {}", quote);

        }

        log.info("Polling Finalizado");
        return ResponseEntity.ok().build();
    }

}

