package br.com.app.mobile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/command")
public class CommandController {

    private final RestTemplate restTemplate;

    public CommandController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity command(@RequestBody RequestQuote request){

        restTemplate.postForObject("http://localhost:8099/insurance/quote/", request, RequestQuote.class);

        return ResponseEntity.ok().build();
    }

}

