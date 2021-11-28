package br.com.insurance.market.adapters.controller;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private  QuoteRepository quoteRepository;

    public void createQuoteTest(final Quote quote ){
        quoteRepository.save(quote);
    }
}
