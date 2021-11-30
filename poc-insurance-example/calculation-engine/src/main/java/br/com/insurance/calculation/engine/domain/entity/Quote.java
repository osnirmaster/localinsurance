package br.com.insurance.calculation.engine.domain.entity;

import java.util.List;

public class Quote {

    private String customerId;
    private String quoteId;
    private String productCode;
    private CreditContract creditContract;
    private List<Parcel> parcel ;

    public Quote() {
    }

    public Quote(String customerId, String quoteId, String productCode, CreditContract creditContract, List<Parcel> parcel) {
        this.customerId = customerId;
        this.quoteId = quoteId;
        this.productCode = productCode;
        this.creditContract = creditContract;
        this.parcel = parcel;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public CreditContract getCreditContract() {
        return creditContract;
    }

    public void setCreditContract(CreditContract creditContract) {
        this.creditContract = creditContract;
    }

    public List<Parcel> getParcel() {
        return parcel;
    }

    public void setParcel(List<Parcel> parcel) {
        this.parcel = parcel;
    }
}
