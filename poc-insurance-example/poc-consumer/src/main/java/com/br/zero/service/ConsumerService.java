package com.br.zero.service;

import com.amazonaws.AmazonServiceException;
import com.br.zero.domain.Apolice;
import com.br.zero.infra.db.repositories.ApoliceRepository;
import com.br.zero.infra.db.repositories.config.DynamodbConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Value("${app.tempo-nack-ms}")
    private long tempoNackMs;
    private static final Logger LOGGER = LogManager.getLogger(ConsumerService.class);
    @Autowired
    private ApoliceRepository apoliceRepository;

    @KafkaListener(topics = "${app.topico-kafka}")
    public void consumir(@Payload Apolice message,
                         @Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topico,
                         @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                         Acknowledgment ack){

        try{
            LOGGER.info("Inserindo na base: ", message);
            apoliceRepository.save(message);

        }catch (Exception e){

            if (e.getCause() instanceof AmazonServiceException){
                LOGGER.error("Problemas ao comunicar com a base de dados, tentaremos novamente em 10segundos", e);
                ack.nack(tempoNackMs);
        }else {
                LOGGER.error("Erro desconhecido ao tentar salvar", e);
                ack.acknowledge();
            }
    }
}
}
