package br.com.insurance.reactive.quote.service.api;

import br.com.insurance.reactive.quote.model.Customer;
import br.com.insurance.reactive.quote.model.GetCustomer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GetCustomerBank implements GetCustomer {
    @Override
    public Customer getCustomer(String customerId) {
        return new Customer(
                101010,
                "Osnir",
                "Teixeira",
                LocalDate.of(1988,12, 8),
                null
        );
    }
}
