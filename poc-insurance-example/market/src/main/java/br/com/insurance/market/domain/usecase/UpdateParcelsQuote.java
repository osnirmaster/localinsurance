package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UpdateParcelsQuote {

    private final QuoteRepository quoteRepository;

    public UpdateParcelsQuote(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote includeParcel(Quote quoteUpdated){
        QuoteId id = new QuoteId(quoteUpdated.getCustomerId(), quoteUpdated.getQuoteId());
        log.info("Objeto quote updated: {}", quoteUpdated);
        Optional<Quote> quote = quoteRepository.findById(id);

        if(quote.isEmpty()){
            throw new RuntimeException("Quote nao encontrada");
        }

        log.info("Objeto quote: {}", quote);

        quote.get()
                .getCreditContractParcel()
                .add(quoteUpdated.getCreditContractParcel().get(0));

        return quoteRepository.save(quote.get());

    }
}
