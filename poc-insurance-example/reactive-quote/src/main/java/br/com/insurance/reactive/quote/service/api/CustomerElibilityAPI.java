package br.com.insurance.reactive.quote.service.api;

import br.com.insurance.reactive.quote.model.Customer;
import br.com.insurance.reactive.quote.model.CustomerEligibility;
import org.springframework.stereotype.Component;

@Component
public class CustomerElibilityAPI implements CustomerEligibility {
    @Override
    public boolean getEligibility(Customer customer) {
        return true;
    }
}
