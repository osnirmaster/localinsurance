package br.com.insurance.market.usecase;

import br.com.insurance.market.adapters.dto.RequestQuote;
import br.com.insurance.market.domain.Customer;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.service.CommandBroker;
import br.com.insurance.market.domain.service.CoverTax;
import br.com.insurance.market.domain.service.GetCustomer;
import br.com.insurance.market.domain.service.CustomerEligibility;
import br.com.insurance.market.infra.db.repository.QuoteRepository;

public class GetQuoteCalculation {

    private final QuoteRepository quoteRepository;
    private final GetCustomer getCustomer;
    private final CoverTax coverTax;
    private final CustomerEligibility customerEligibility;
    private final CommandBroker commandBroker;

    public GetQuoteCalculation(QuoteRepository quoteRepository,
                               GetCustomer getCustomer,
                               CoverTax coverTax,
                               CustomerEligibility customerEligibility, CommandBroker commandBroker) {
        this.quoteRepository = quoteRepository;
        this.getCustomer = getCustomer;
        this.coverTax = coverTax;
        this.customerEligibility = customerEligibility;
        this.commandBroker = commandBroker;
    }

    public Quote insuranceQuote(RequestQuote payload){
        Quote quote = payload.convertTo();

        Customer customer = getCustomer.getCustomer(quote.getCustomerId());
        customerEligibility.getEligibility(customer);
        coverTax.getCoverTax(quote.getproductCode());

        quote.sendContractForCaculation(commandBroker);

        return quoteRepository.save(quote);
    }
}
