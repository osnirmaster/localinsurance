package br.com.insurance.calculation.engine.infra.db.spring.repository;

import br.com.insurance.calculation.engine.domain.entity.TermFeeId;
import br.com.insurance.calculation.engine.domain.entity.TermFeeTax;
import br.com.insurance.calculation.engine.domain.repository.TermFeeTaxRespository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SpringTermFeeRepository extends CrudRepository<TermFeeTax, TermFeeId>, TermFeeTaxRespository {

}
