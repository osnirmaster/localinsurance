package br.com.insurance.market.infra.api;

import br.com.insurance.market.domain.service.CoverTax;
import org.springframework.stereotype.Component;

@Component
public class CoverTaxProduct implements CoverTax {
    @Override
    public double getCoverTax(String productCode) {
        return 0.05;
    }
}
