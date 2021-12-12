package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;

import java.util.Optional;

public class GetQuoteFinalized {

    private final QuoteRepository quoteRepository;

    public GetQuoteFinalized(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Optional<Quote> getQuote(QuoteId id){
        return quoteRepository.findById(id);
    }
}
