package br.com.insurance.market.infra.api;

import br.com.insurance.market.domain.Customer;
import br.com.insurance.market.domain.service.CustomerEligibility;
import org.springframework.stereotype.Component;

@Component
public class CustomerElibilityAPI implements CustomerEligibility {
    @Override
    public boolean getEligibility(Customer customer) {
        return true;
    }
}
