package br.com.insurance.market.adapters.controller;

import br.com.insurance.market.adapters.dto.RequestQuote;
import br.com.insurance.market.adapters.dto.ResponseQuote;
import br.com.insurance.market.adapters.dto.UpdateQuote;
import br.com.insurance.market.domain.CreditContractParcel;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.domain.usecase.GetQuoteCalculation;
import br.com.insurance.market.domain.usecase.GetQuoteFinalized;
import br.com.insurance.market.domain.usecase.UpdateParcelsQuote;
import br.com.insurance.market.domain.vo.QuoteStatus;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/insurance/quote")
public class QuoteControllerRest {

    @Autowired
    private  GetQuoteCalculation getQuoteCalculation;
    @Autowired
    private UpdateParcelsQuote updateParcelsQuote;
    @Autowired
    private GetQuoteFinalized quoteFinalized;


    @PostMapping
    public ResponseEntity price(@RequestBody RequestQuote request, UriComponentsBuilder uriBuilder) throws ExecutionException, InterruptedException {

        Quote quote = getQuoteCalculation.insuranceQuote(request.convertTo());
        URI uri = uriBuilder
                .path("/quote/{id}/customerId/{id}")
                .buildAndExpand(quote.getQuoteId(), quote.getCustomerId())
                .toUri();
        return ResponseEntity.created(uri).body(quote);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote( @PathVariable String id ,@RequestBody @Valid UpdateQuote request) throws IOException, InterruptedException {
        log.info("ID recebido: {}", id);
        log.info("request recebido: {}", request);


        Quote quote = updateParcelsQuote.includeParcel(request).convertTo();
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/{quoteId}/customer/{customerId}")
    public ResponseEntity<ResponseQuote> getQuote(@PathVariable String quoteId, @PathVariable String customerId, @RequestParam Integer contracts){
        QuoteId key = new QuoteId();
        key.setCustomerId(customerId);
        key.setQuoteId(quoteId);

        PageIterable<CreditContractParcel> updatedQuote = quoteFinalized.getQuoteWithParcels(key.getQuoteId());

        ResponseQuote response = new ResponseQuote(customerId, quoteId, QuoteStatus.PENDENT, updatedQuote.iterator().next().items());

        log.info("numero contratos calculados: {} e parameter {}", response.getContracts().stream().count(), contracts );
        if(response.getContracts().stream().count() == contracts){
            response.setStatusQuote(QuoteStatus.FINISHED);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(response);

    }
}
