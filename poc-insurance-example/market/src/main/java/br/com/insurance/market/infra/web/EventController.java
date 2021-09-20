package br.com.insurance.market.infra.web;

import br.com.insurance.market.infra.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private KafkaProducer producer;

    @GetMapping
    public String registerEvent() throws ExecutionException, InterruptedException {
        producer.sendMessage("teste payload");
        return "ok";
    }
}
