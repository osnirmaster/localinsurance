package br.com.insurance.calculation.engine.domain.entity;


import br.com.insurance.calculation.engine.domain.CreditContract;

public class InsuranceCalculate {

    private String customerId;
    private String quoteId;
    private Double coverTax;
    private CreditContract creditContract;


    public void addCreditContract(CreditContract contract){
        this.creditContract = contract;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public Double getCoverTax() {
        return coverTax;
    }

    public CreditContract getCreditContract() {
        return creditContract;
    }
}
