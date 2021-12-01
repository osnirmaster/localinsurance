package br.com.insurance.calculation.engine.config;

import br.com.insurance.calculation.engine.domain.usecase.CalculationByContractCredit;
import br.com.insurance.calculation.engine.infra.db.spring.repository.SpringTermFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {
    @Autowired
    private  SpringTermFeeRepository termFeeTaxRespository;

    @Bean
    public CalculationByContractCredit calculation(){
        return new CalculationByContractCredit(termFeeTaxRespository);
    }
}
