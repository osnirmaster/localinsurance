package br.com.insurance.market.infra.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topicCreate() {
        return new NewTopic("command-quote-calculator", 3, (short) 1);
    }
}
