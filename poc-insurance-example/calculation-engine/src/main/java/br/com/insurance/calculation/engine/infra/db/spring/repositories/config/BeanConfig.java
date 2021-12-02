package br.com.insurance.calculation.engine.infra.db.spring.repositories.config;

import br.com.insurance.calculation.engine.domain.repository.TermFeeTaxRespository;
import br.com.insurance.calculation.engine.domain.usecase.CalculationByContractCredit;
import br.com.insurance.calculation.engine.infra.db.spring.repositories.CustomTermFeeRepository;
import br.com.insurance.calculation.engine.infra.db.spring.repositories.SpringTermFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    private final  SpringTermFeeRepository termFeeTaxRespository;

    public BeanConfig(SpringTermFeeRepository termFeeTaxRespository) {
        this.termFeeTaxRespository = termFeeTaxRespository;
    }

    @Bean
    public CalculationByContractCredit calculation(){
        return new CalculationByContractCredit(termFeeTaxRespository);
    }
}
