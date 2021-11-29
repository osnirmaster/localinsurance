package br.com.insurance.market.infra.kafka;

import br.com.insurance.market.domain.CreditContract;
import br.com.insurance.market.domain.Quote;

import java.math.BigDecimal;

public class CommandCalculationSchemaJson {

    private String customerId;
    private String quoteId;
    private Double coverTax;
    private CreditContract creditContract;


    public CommandCalculationSchemaJson(Quote quote){
        this.customerId = quote.getCustomerId();
        this.quoteId = quote.getQuoteId();
        this.coverTax = quote.getCoverTax();
    }


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
