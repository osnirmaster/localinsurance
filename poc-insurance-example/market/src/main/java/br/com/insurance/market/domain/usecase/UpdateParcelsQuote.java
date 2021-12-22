package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.infra.db.repositories.LockItemService;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import com.amazonaws.services.dynamodbv2.LockItem;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.Buffer;

@Slf4j
public class UpdateParcelsQuote {

    private final QuoteRepository quoteRepository;
    private final LockItemService lockItemService;

    public UpdateParcelsQuote(QuoteRepository quoteRepository, LockItemService lockItemService) {
        this.quoteRepository = quoteRepository;
        this.lockItemService = lockItemService;
    }

    public Quote includeParcel(Quote quoteUpdated) throws IOException, InterruptedException {

        QuoteId id = new QuoteId(quoteUpdated.getCustomerId(), quoteUpdated.getQuoteId());
        Quote quote = null;

        try{
           Buffer data =  lockItemService.getLockItem(id, quoteUpdated).getData().get();

           log.info("Buffer: {}", data);
            
        }catch (Exception ex){
            log.error(String.valueOf(ex));
            log.info("erro: {}", ex);
        }

        return quote;
    }
}
