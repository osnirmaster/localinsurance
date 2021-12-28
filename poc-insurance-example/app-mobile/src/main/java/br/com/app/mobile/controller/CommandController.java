package br.com.app.mobile.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/command")
public class CommandController {

    private final RestTemplate restTemplate;
    @Value("${app.request.quote}")
    private String hostQuoteServer;
    private String response;

    public CommandController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity command(@RequestBody RequestQuote request) {

        log.info("Solicitando cotacao...");
        ResponseCommandQuote responseQuote = restTemplate
                .postForObject(hostQuoteServer+"/insurance/quote/", request, ResponseCommandQuote.class);

        log.info("Iniciando  Polling da cotacao...");
        ResponseQuote response = new ResponseQuote();
        response.setStatusQuote(QuoteStatus.PENDENT);

        while (!response.getStatusQuote().equals(QuoteStatus.FINISHED)) {

            log.info("Fazendo Polling ->");
            response = restTemplate
                    .getForObject(
                            hostQuoteServer +
                                    "/insurance/quote/" +
                                    responseQuote.getQuoteId() +
                                    "/customer/" +
                                    responseQuote.getCustomerId() +
                                    "?contracts=" + request.getContracts().stream().count(),
                            ResponseQuote.class);
            log.info("Response -> {}", response);

        }

        log.info("Polling Finalizado");
        return ResponseEntity.ok(response);
    }

}

