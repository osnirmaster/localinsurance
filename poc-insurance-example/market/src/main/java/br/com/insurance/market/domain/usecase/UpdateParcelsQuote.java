package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.adapters.dto.UpdateQuote;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;


@Slf4j
public class UpdateParcelsQuote {

    private final QuoteRepository quoteRepository;

    public UpdateParcelsQuote(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public UpdateQuote includeParcel(UpdateQuote quoteUpdated) throws IOException, InterruptedException {

        QuoteId id = new QuoteId(quoteUpdated.getCustomerId(), quoteUpdated.getQuoteId());

        Quote quote = quoteRepository.findById(id);

        if(quote != null) quoteRepository.saveParcel(quoteUpdated.getCreditContractParcel().convertKeys());

        return quoteUpdated;
    }
}
