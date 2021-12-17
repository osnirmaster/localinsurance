package br.com.insurance.market.domain.usecase;

import br.com.insurance.market.domain.Customer;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.service.CommandBroker;
import br.com.insurance.market.domain.service.CoverTax;
import br.com.insurance.market.domain.service.GetCustomer;
import br.com.insurance.market.domain.service.CustomerEligibility;
import br.com.insurance.market.infra.db.repositories.QuoteRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
public class GetQuoteCalculation {

    private final QuoteRepository quoteRepository;
    private final GetCustomer getCustomer;
    private final CoverTax coverTax;
    private final CustomerEligibility customerEligibility;
    private final CommandBroker commandBroker;

    public GetQuoteCalculation(QuoteRepository quoteRepository,
                               GetCustomer getCustomer,
                               CoverTax coverTax,
                               CustomerEligibility customerEligibility,
                               CommandBroker commandBroker) {
        this.quoteRepository = quoteRepository;
        this.getCustomer = getCustomer;
        this.coverTax = coverTax;
        this.customerEligibility = customerEligibility;
        this.commandBroker = commandBroker;
    }

    public Quote insuranceQuote(Quote quote) throws ExecutionException, InterruptedException {

        Customer customer = getCustomer.getCustomer(quote.getCustomerId());
        if(customerEligibility.getEligibility(customer)) {

            quote.setCoverTax(coverTax.getCoverTax(quote.getproductCode()));
            quote.setQuoteId(UUID.randomUUID().toString());
            quote.sendContractForCaculation(commandBroker);
            log.info("Mapeamento objeto {}", quote );
            log.info("Preparando para armazenar...");
        }


        return quoteRepository.save(quote);
    }
}
