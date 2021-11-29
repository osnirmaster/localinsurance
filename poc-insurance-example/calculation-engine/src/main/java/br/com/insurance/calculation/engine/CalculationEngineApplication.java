package br.com.insurance.calculation.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class CalculationEngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculationEngineApplication.class, args);
    }
}
