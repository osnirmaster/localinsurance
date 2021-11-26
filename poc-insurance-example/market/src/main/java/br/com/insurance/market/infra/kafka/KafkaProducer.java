package br.com.insurance.market.infra.kafka;

import br.com.insurance.market.domain.CreditContract;
import br.com.insurance.market.domain.service.CommandBroker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class KafkaProducer implements CommandBroker {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<Integer, CreditContract> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<Integer, CreditContract> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CreditContract contract) throws ExecutionException, InterruptedException {
        log.info("Payload: {}", contract);
        kafkaTemplate
                .send(topicName, contract.getCreditAgreementId(), contract)
                .addCallback(new ListenableFutureCallback<SendResult<Integer, CreditContract>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<Integer, CreditContract> result) {
                log.info("Resultado: {}, {}, {}, {}", result.getProducerRecord().key(),
                        result.getProducerRecord().headers(),
                        result.getProducerRecord().topic(),
                        result.getProducerRecord().hashCode());
            }
        });
    }

    @Override
    public void sendCommand(List<CreditContract> contractList) throws ExecutionException, InterruptedException {
        for (CreditContract contract : contractList){
            sendMessage(contract);
        }
    }
}
