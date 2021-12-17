package br.com.insurance.calculation.engine.domain.repository;

import br.com.insurance.calculation.engine.domain.entity.TermFeeId;
import br.com.insurance.calculation.engine.domain.entity.TermFeeTax;
import java.util.Optional;

public interface TermFeeTaxRespository {
    Optional<TermFeeTax> getTermFee(TermFeeId partitionKey);
}
