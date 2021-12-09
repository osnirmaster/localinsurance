package br.com.insurance.reactive.quote.service.api;

import br.com.insurance.reactive.quote.model.CoverTax;
import org.springframework.stereotype.Component;

@Component
public class CoverTaxProduct implements CoverTax {
    @Override
    public double getCoverTax(String productCode) {
        return 0.05;
    }
}
