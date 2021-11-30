package br.com.insurance.calculation.engine.domain.repository;

import br.com.insurance.calculation.engine.domain.entity.TermFeeId;
import br.com.insurance.calculation.engine.domain.entity.TermFeeTax;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

public interface TermFeeTaxRespository {
    PaginatedQueryList<TermFeeTax> getTermFee(TermFeeId partitionKey);
}
