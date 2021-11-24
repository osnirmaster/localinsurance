package br.com.insurance.market.adapters.dto;

import br.com.insurance.market.domain.CreditContract;
import br.com.insurance.market.domain.Quote;

import java.time.LocalDate;
import java.util.List;

public class RequestQuote {

    private String productCode;
    private LocalDate dataQuota;
    private LocalDate birthDate;
    private String customerClient;
    private String idCustomer;
    private List<CreditContract> contracts;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public LocalDate getDataQuota() {
        return dataQuota;
    }

    public void setDataQuota(LocalDate dataQuota) {
        this.dataQuota = dataQuota;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCustomerClient() {
        return customerClient;
    }

    public void setCustomerClient(String customerClient) {
        this.customerClient = customerClient;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public List<CreditContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<CreditContract> contracts) {
        this.contracts = contracts;
    }

    public Quote convertTo(){
        return new Quote();
    }
}
