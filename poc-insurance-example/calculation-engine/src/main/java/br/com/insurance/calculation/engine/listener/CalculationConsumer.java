package br.com.insurance.calculation.engine.listener;

import br.com.insurance.calculation.engine.domain.entity.InsuranceCalculate;
import br.com.insurance.calculation.engine.domain.entity.Quote;
import br.com.insurance.calculation.engine.domain.usecase.CalculationByContractCredit;
import com.amazonaws.AmazonServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CalculationConsumer {
    @Value("${app.tempo-nack-ms}")
    private long tempoNackMs;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;
    private final CalculationByContractCredit calculation;

    public CalculationConsumer(RestTemplate restTemplate, CalculationByContractCredit calculation) {
        this.restTemplate = restTemplate;
        this.calculation = calculation;
    }


    @KafkaListener(topics = "${app.topico-cliente}")
    public void consumir(@Payload InsuranceCalculate message,
                         @Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topico,
                         @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                         Acknowledgment ack){
        try{
            logger.info("Iniciando consumo do tópico {}, key {}, Numero do contrato {}", topico, key, message.getCreditContract().getCreditAgreementId() );
            calculation.toCalculate(message);
            restTemplate.patchForObject("http://localhost:8095/insurance/quote", ,Quote.class);

            ack.acknowledge();
            logger.info("Commit realizado");
        }catch (Exception e){
            /*
             * Se ocorrer um erro de conexão com a base (ex: timeout por firewall, credenciais inválidas ..) então tentaremos
             * novamente pois não adianta passar para a próxima mensagem, já que todas precisam ser salvas na base.
             * */
            if (e.getCause() instanceof AmazonServiceException){
                logger.error("Problemas ao comunicar com a base de dados, tentaremos novamente em 10segundos", e);
                ack.nack(tempoNackMs);
            }else {
                /*
                 * Qualquer outro erro não mapeado, estamos apenas dando commit na mensagem.
                 * Que tal um deadletter aqui ?? :D
                 * */
                logger.error("Erro desconhecido ao tentar salvar", e);
                ack.acknowledge();
            }
        }

    }
}