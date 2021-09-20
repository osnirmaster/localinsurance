package br.com.insurance.customer.infra.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerSpring {

    @Value("${app.tempo-nack-ms}")
    private long tempoNackMs;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "${topic.name.producer}")
    public void consumerEvent(String payload){
        log.info("Consumindo Registros: {}", payload);
    }

}
