package br.com.insurance.market.usecase;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.infra.db.repository.QuoteRepository;

public class GetQuoteCalculation {

    private final QuoteRepository quoteRepository;

    public GetQuoteCalculation(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote insuranceQuote(Quote quote){
        return quoteRepository.save(quote);
    }
}
