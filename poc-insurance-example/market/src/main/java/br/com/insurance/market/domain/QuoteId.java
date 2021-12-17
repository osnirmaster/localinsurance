package br.com.insurance.market.domain;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@DynamoDbBean
public class QuoteId implements Serializable {

    private static final long serialVersionUID = 1L;

    public String customerId;
    public String quoteId;

    public QuoteId(){}

    public QuoteId(String customerId, String quoteId){
        this.customerId = customerId;
        this.quoteId = quoteId;
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
}
