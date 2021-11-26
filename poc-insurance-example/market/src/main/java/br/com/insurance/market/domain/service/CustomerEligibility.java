package br.com.insurance.market.domain.service;

import br.com.insurance.market.domain.Customer;

public interface CustomerEligibility {
    boolean getEligibility(Customer customer);
}
