package br.com.insurance.market.adapters.dto;

import br.com.insurance.market.domain.CreditContract;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.infra.db.QuoteId;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RequestQuote {

    private String productCode;
    private LocalDate birthDate;
    private Integer customerId;
    private String segmentCustomerCode;
    private List<CreditContract> contracts;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<CreditContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<CreditContract> contracts) {
        this.contracts = contracts;
    }

    public String getSegmentCustomerCode() {
        return segmentCustomerCode;
    }

    public void setSegmentCustomerCode(String segmentCustomerCode) {
        this.segmentCustomerCode = segmentCustomerCode;
    }

    public Quote convertTo(){
        QuoteId quoteId = new QuoteId(this.customerId, UUID.randomUUID());

        return new Quote(
                quoteId,
                this.productCode,
                this.birthDate,
                this.customerId,
                this.segmentCustomerCode,
                this.contracts);
    }
}
