package br.com.app.mobile.controller;

import java.util.ArrayList;
import java.util.List;

public class ResponseQuote {
    private String customerId;
    private String quoteId;
    private QuoteStatus statusQuote;
    private List<CreditContractParcel> contracts = new ArrayList<>();

    public ResponseQuote(){}

    public ResponseQuote(String customerId, String quoteId, QuoteStatus statusQuote, List<CreditContractParcel> contracts) {
        this.customerId = customerId;
        this.quoteId = quoteId;
        this.statusQuote = statusQuote;
        this.contracts = contracts;
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

    public QuoteStatus getStatusQuote() {
        return statusQuote;
    }

    public void setStatusQuote(QuoteStatus statusQuote) {
        this.statusQuote = statusQuote;
    }

    public List<CreditContractParcel> getContracts() {
        return contracts;
    }

    public void setContracts(List<CreditContractParcel> contracts) {
        this.contracts = contracts;
    }


    @Override
    public String toString() {
        return "ResponseQuote{" +
                "customerId='" + customerId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                ", statusQuote=" + statusQuote +
                '}';
    }
}
