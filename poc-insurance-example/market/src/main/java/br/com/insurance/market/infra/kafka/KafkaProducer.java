package br.com.insurance.market.infra.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class KafkaProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) throws ExecutionException, InterruptedException {
        log.info("Payload: {}", message);
        kafkaTemplate.send(topicName, UUID.randomUUID().toString(),message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Resultado: {}, {}, {}, {}", result.getProducerRecord().key(),
                        result.getProducerRecord().headers(),
                        result.getProducerRecord().topic(),
                        result.getProducerRecord().hashCode());
            }
        });
    }
}
