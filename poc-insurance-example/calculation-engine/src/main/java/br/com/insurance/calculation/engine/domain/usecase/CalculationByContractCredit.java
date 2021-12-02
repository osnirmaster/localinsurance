package br.com.insurance.calculation.engine.domain.usecase;

import br.com.insurance.calculation.engine.domain.entity.*;
import br.com.insurance.calculation.engine.infra.db.spring.repositories.SpringTermFeeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CalculationByContractCredit {

    private final SpringTermFeeRepository termFeeTaxRespository;

    public CalculationByContractCredit(SpringTermFeeRepository termFeeTaxRespository) {
        this.termFeeTaxRespository = termFeeTaxRespository;
    }

    public UpdateQuote toCalculate(InsuranceCalculate quote){

        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i <= quote.getCreditContract().getCreditParcelAmount() ; i++) {
           Optional<TermFeeTax> tax = termFeeTaxRespository
                    .findById(new TermFeeId(quote.getProductCode(),
                            quote.getCreditContract().getCreditParcelAmount()));
           if(tax.isPresent()){
               BigDecimal priceTaxFee = quote.getCreditContract()
                       .getCreditPriceTotal()
                       .multiply(BigDecimal.valueOf(tax.get().getTax()));
               BigDecimal priceCoverTax = quote.getCreditContract()
                       .getCreditPriceTotal()
                       .multiply(BigDecimal.valueOf(quote.getCoverTax()));

               parcels.add(new Parcel(i, priceCoverTax.add(priceTaxFee)));
           }

           return new UpdateQuote(
                   quote.getCustomerId(),
                   quote.getQuoteId(),
                   quote.getProductCode(),
                   new CreditContractParcel(quote.getCreditContract()
                           .getCreditAgreementId(),
                           parcels)
           );
        }
        return new UpdateQuote();
    }
}
