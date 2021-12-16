package br.com.insurance.calculation.engine.infra.db.spring.repositories;

import br.com.insurance.calculation.engine.domain.entity.TermFeeId;
import br.com.insurance.calculation.engine.domain.entity.TermFeeTax;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface SpringTermFeeRepository extends CrudRepository<TermFeeTax, TermFeeId> {

}
