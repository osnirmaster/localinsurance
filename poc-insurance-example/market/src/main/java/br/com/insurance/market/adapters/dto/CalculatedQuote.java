package br.com.insurance.market.adapters.dto;

import br.com.insurance.market.domain.CreditContract;
import br.com.insurance.market.domain.Parcel;

import java.util.List;

public class CalculatedQuote {

    private String customerId;
    private String quoteId;
    private String productCode;
    private List<Parcel> parcel ;

    public CalculatedQuote() {
    }

    public CalculatedQuote(String customerId, String quoteId, String productCode, List<Parcel> parcel) {
        this.customerId = customerId;
        this.quoteId = quoteId;
        this.productCode = productCode;
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


    public List<Parcel> getParcel() {
        return parcel;
    }

    public void setParcel(List<Parcel> parcel) {
        this.parcel = parcel;
    }

    @Override
    public String toString() {
        return "Value{" +
                "customerId='" + customerId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", parcel=" + parcel +
                '}';
    }
}
