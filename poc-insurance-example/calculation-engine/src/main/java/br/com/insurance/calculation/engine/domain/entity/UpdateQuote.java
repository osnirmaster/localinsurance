package br.com.insurance.calculation.engine.domain.entity;

public class UpdateQuote {

    private String customerId;
    private String quoteId;
    private String productCode;
    private CreditContractParcel creditContractParcel;

    public UpdateQuote() {
    }

    public UpdateQuote(String customerId, String quoteId, String productCode, CreditContractParcel creditContractParcel) {
        this.customerId = customerId;
        this.quoteId = quoteId;
        this.productCode = productCode;
        this.creditContractParcel = creditContractParcel;
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

    public CreditContractParcel getCreditContractParcel() {
        return creditContractParcel;
    }

    public void setCreditContractParcel(CreditContractParcel creditContractParcel) {
        this.creditContractParcel = creditContractParcel;
    }


    @Override
    public String toString() {
        return "Value{" +
                "customerId='" + customerId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", creditContractParcel=" + creditContractParcel +
                '}';
    }
}
