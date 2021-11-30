package br.com.insurance.calculation.engine.domain.entity;


public class InsuranceCalculate {

    private String customerId;
    private String quoteId;
    private Double coverTax;
    private String productCode;
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

    public void setCoverTax(Double coverTax) {
        this.coverTax = coverTax;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setCreditContract(CreditContract creditContract) {
        this.creditContract = creditContract;
    }
}
