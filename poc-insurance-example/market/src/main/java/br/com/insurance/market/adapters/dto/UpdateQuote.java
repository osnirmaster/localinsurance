package br.com.insurance.market.adapters.dto;

import br.com.insurance.market.domain.CreditContractParcel;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.infra.db.QuoteId;

import java.util.ArrayList;
import java.util.List;

public class UpdateQuote {

    private String customerId;
    private String quoteId;
    private String productCode;
    private CreditContractParcel creditContractParcel;


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
        return "Values{" +
                "customerId='" + customerId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", creditContractParcel=" + creditContractParcel +
                '}';
    }

    public Quote convertTo(){
        QuoteId id = new QuoteId();
        id.setCustomerId(this.customerId);
        id.setQuoteId(this.quoteId);

        return new Quote(
                id,
                this.productCode,
                this.creditContractParcel);
    }
}
