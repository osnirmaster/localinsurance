package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.domain.vo.QuoteStatus;
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
        Optional<Quote> quote = quoteRepository.findById(id);

        if(quote.isEmpty()){
            throw new RuntimeException("Cotação nao encontrada");
        }


        quote.get()
                .getCreditContractParcel()
                .add(quoteUpdated.getCreditContractParcel().get(0));


        Integer contractsNumber = Math.toIntExact(quote.get().getCreditContracts().stream().count());
        Integer contractsUpdated = Math.toIntExact(quote.get().getCreditContractParcel().stream().count());

        log.info("Contratos Enviados: {} vs {} Contratos Calculados", contractsNumber, contractsUpdated);

        if (contractsUpdated.equals(contractsNumber) ){
            quote.get().setStatus(QuoteStatus.FINISHED);
            log.info("Calculo Quotacao finalizada", contractsNumber, contractsUpdated);
        }

        return quoteRepository.save(quote.get());

    }
}
