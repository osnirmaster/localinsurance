package br.com.insurance.calculation.engine.domain.usecase;

import br.com.insurance.calculation.engine.domain.entity.*;
import br.com.insurance.calculation.engine.infra.db.spring.repositories.CustomTermFeeRepository;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CalculationByContractCredit {

    private final CustomTermFeeRepository termFeeTaxRespository;

    public CalculationByContractCredit(CustomTermFeeRepository termFeeTaxRespository) {
        this.termFeeTaxRespository = termFeeTaxRespository;
    }

    public UpdateQuote toCalculate(InsuranceCalculate quote){

        List<TermFeeTax> tax = termFeeTaxRespository
                .queryTax(new TermFeeId(quote.getProductCode(),
                        quote.getCreditContract().getCreditParcelAmount())).items().stream().collect(Collectors.toList());

        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i < quote.getCreditContract().getCreditParcelAmount() ; i++) {
          try{
           if(!tax.isEmpty()){
               BigDecimal priceTaxFee = quote.getCreditContract()
                       .getCreditPriceTotal()
                       .multiply(BigDecimal.valueOf(tax.get(i).getTax()));
               BigDecimal priceCoverTax = quote.getCreditContract()
                       .getCreditPriceTotal()
                       .multiply(BigDecimal.valueOf(quote.getCoverTax()));
               log.info("ammount: {}, parcela: {}",  quote.getCreditContract().getCreditParcelAmount(),i);
               parcels.add(new Parcel(i+1,
                       priceCoverTax.add(priceTaxFee)));
           }
          } catch (Exception e) {
              e.printStackTrace();
          }
        }
        return new UpdateQuote(
                quote.getCustomerId(),
                quote.getQuoteId(),
                quote.getProductCode(),
                new CreditContractParcel(
                        quote.getQuoteId(),
                        quote.getCreditContract().getCreditAgreementId(),
                        parcels)
        );
    }
}
