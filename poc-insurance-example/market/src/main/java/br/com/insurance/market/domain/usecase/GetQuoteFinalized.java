package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.domain.CreditContractParcel;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.Optional;

public class GetQuoteFinalized {

    private final QuoteRepository quoteRepository;

    public GetQuoteFinalized(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public PageIterable<CreditContractParcel> getQuoteWithParcels(String id){
        return quoteRepository.getContractParcel(id);
    }
}
