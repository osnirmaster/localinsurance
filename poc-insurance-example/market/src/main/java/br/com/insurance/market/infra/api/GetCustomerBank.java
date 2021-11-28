package br.com.insurance.market.infra.api;

import br.com.insurance.market.domain.Customer;
import br.com.insurance.market.domain.service.GetCustomer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GetCustomerBank implements GetCustomer {
    @Override
    public Customer getCustomer(Integer customerId) {
        return new Customer(
                101010,
                "Osnir",
                "Teixeira",
                LocalDate.of(1988,12, 8),
                null
        );
    }
}
