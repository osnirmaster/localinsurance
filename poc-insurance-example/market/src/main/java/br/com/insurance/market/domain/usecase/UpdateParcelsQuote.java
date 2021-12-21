package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.domain.vo.QuoteStatus;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class UpdateParcelsQuote {

    private final QuoteRepository quoteRepository;

    public UpdateParcelsQuote(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote includeParcel(Quote quoteUpdated) throws IOException, InterruptedException {

        QuoteId id = new QuoteId(quoteUpdated.getCustomerId(), quoteUpdated.getQuoteId());

        Quote quote = null;

        quoteRepository.getLockItem(id);
        
        try{
             quoteRepository.getLockItem(id);
             quote = quoteRepository.findById(id);

            if(quote == null){
                throw new Exception("Cotação nao encontrada");
            }
            
        }catch (Exception ex){
            log.error(String.valueOf(ex));
            log.info("erro: {}", ex);
        }

        quote.
                getCreditContractParcel().add(quoteUpdated.getCreditContractParcel().get(0));

        Integer contractsNumber = Math.toIntExact(quote.getCreditContracts().stream().count());
        Integer contractsUpdated = Math.toIntExact(quote.getCreditContractParcel().stream().count());

        log.info("Contratos Enviados: {} vs {} Contratos Calculados", contractsNumber, contractsUpdated);
        log.info("quote id: {}, credit id: {}", quoteUpdated.getQuoteId(), quoteUpdated.getCreditContractParcel().get(0).getCreditAgreementId() );
        if (contractsUpdated.equals(contractsNumber) ){
            quote.setStatus(QuoteStatus.FINISHED);
            log.info("Calculo Quotacao finalizada", contractsNumber, contractsUpdated);
        }

        try{

            return quoteRepository.save(quote);
        }catch (ConditionalCheckFailedException ex){
            log.info("Versão do objeto antiga, realinzando nova tentativa");

            return quoteRepository.save(quote);
        }

    }
}
