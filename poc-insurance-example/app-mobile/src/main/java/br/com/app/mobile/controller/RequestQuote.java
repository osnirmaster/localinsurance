package br.com.app.mobile.controller;


import java.util.List;

public class RequestQuote {

    private String productCode;
    private String birthDate;
    private String customerId;
    private String segmentCustomerCode;
    private List<CreditContract> contracts;
    private List<CalculatedQuote> calculatedQuotes;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<CreditContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<CreditContract> contracts) {
        this.contracts = contracts;
    }

    public String getSegmentCustomerCode() {
        return segmentCustomerCode;
    }

    public void setSegmentCustomerCode(String segmentCustomerCode) {
        this.segmentCustomerCode = segmentCustomerCode;
    }

    public Quote convertTo(){
        QuoteId quoteId = new QuoteId();
        quoteId.setCustomerId(this.customerId);

        return new Quote(
                quoteId,
                this.productCode,
                this.birthDate,
                this.segmentCustomerCode,
                this.contracts);
    }
}
