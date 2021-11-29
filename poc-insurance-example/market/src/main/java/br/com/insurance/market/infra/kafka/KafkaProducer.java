package br.com.insurance.market.infra.kafka;

import br.com.insurance.market.domain.CreditContract;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.service.CommandBroker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class KafkaProducer implements CommandBroker {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, CommandCalculationSchemaJson> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, CommandCalculationSchemaJson> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CommandCalculationSchemaJson command) throws ExecutionException, InterruptedException {
        log.info("Payload: {}", command);
        kafkaTemplate
                .send(topicName, command.getCreditContract().getCreditAgreementId(), command)
                .addCallback(new ListenableFutureCallback<SendResult<String, CommandCalculationSchemaJson>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, CommandCalculationSchemaJson> result) {
                log.info("Resultado: {}, {}, {}, {}", result.getProducerRecord().key(),
                        result.getProducerRecord().headers(),
                        result.getProducerRecord().topic(),
                        result.getProducerRecord().hashCode());
            }
        });
    }

    @Override
    public void sendCommand(Quote quote) throws ExecutionException, InterruptedException {
        for (CreditContract contract : quote.getCreditContracts()){
            CommandCalculationSchemaJson command = new CommandCalculationSchemaJson(quote);
            command.addCreditContract(contract);
            sendMessage(command);
        }
    }
}
