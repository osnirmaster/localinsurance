package br.com.insurance.market.adapters.dto;

import br.com.insurance.market.domain.CreditContract;

import java.time.LocalDate;
import java.util.List;

public class RequestQuote {

    private String productCode;
    private LocalDate dataQuota;
    private LocalDate birthDate;
    private String customerClient;
    private String idCustomer;
    private List<CreditContract> contracts;

}
