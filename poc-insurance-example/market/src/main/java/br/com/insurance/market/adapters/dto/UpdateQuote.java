package br.com.insurance.market.adapters.dto;

import br.com.insurance.market.domain.CreditContractParcel;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.infra.db.QuoteId;

public class UpdateQuote {

    private String customerId;
    private String quoteId;
    private String productCode;
    private CreditContractParcel creditContractParcel;


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
