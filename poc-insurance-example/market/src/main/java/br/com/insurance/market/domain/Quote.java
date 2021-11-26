package br.com.insurance.market.domain;

import br.com.insurance.market.domain.service.CommandBroker;
import br.com.insurance.market.domain.vo.QuoteStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Quote")
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "quote_id", updatable = false, nullable = false)
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID quoteId;
    private String productCode;
    @Enumerated(EnumType.STRING)
    private QuoteStatus status = QuoteStatus.PENDENT;
    private BigDecimal insurancePriceTotal;
    private BigDecimal insurancePriceParcel;
    private List<CreditContract> creditContracts;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateQuote;
    private Integer customerId;
    private String segmentCustomerCode;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDateCustomer;

    public Quote(){}

    public Quote(String productCode,
                 LocalDate birthDateCustomer,
                 Integer customerId,
                 String segmentCustomerCode,
                 List<CreditContract> creditContracts

                 ) {
        this.productCode = productCode;
        this.creditContracts = creditContracts;
        this.customerId = customerId;
        this.segmentCustomerCode = segmentCustomerCode;
        this.birthDateCustomer = birthDateCustomer;
    }

    public Quote(String productCode,
                 BigDecimal insurancePriceTotal,
                 BigDecimal insurancePriceParcel,
                 List<CreditContract> creditContracts,
                 LocalDate dateQuote,
                 Integer customerId,
                 LocalDate birthDateCustomer) {
        this.productCode = productCode;
        this.insurancePriceTotal = insurancePriceTotal;
        this.insurancePriceParcel = insurancePriceParcel;
        this.creditContracts = creditContracts;
        this.dateQuote = dateQuote;
        this.customerId = customerId;
        this.birthDateCustomer = birthDateCustomer;
    }

    public UUID getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(UUID quoteId) {
        this.quoteId = quoteId;
    }

    public String getproductCode() {
        return productCode;
    }

    public void setproductCode(String productCode) {
        this.productCode = productCode;
    }

    public QuoteStatus getStatus() {
        return status;
    }

    public void setStatus(QuoteStatus status) {
        this.status = status;
    }

    public BigDecimal getInsurancePriceTotal() {
        return insurancePriceTotal;
    }

    public void setInsurancePriceTotal(BigDecimal insurancePriceTotal) {
        this.insurancePriceTotal = insurancePriceTotal;
    }

    public BigDecimal getInsurancePriceParcel() {
        return insurancePriceParcel;
    }

    public void setInsurancePriceParcel(BigDecimal insurancePriceParcel) {
        this.insurancePriceParcel = insurancePriceParcel;
    }

    public LocalDate getDateQuote() {
        return dateQuote;
    }

    public void setDateQuote(LocalDate dateQuote) {
        this.dateQuote = dateQuote;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getBirthDateCustomer() {
        return birthDateCustomer;
    }

    public void setBirthDateCustomer(LocalDate birthDateCustomer) {
        this.birthDateCustomer = birthDateCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(quoteId, quote.quoteId) && Objects.equals(productCode, quote.productCode) && status == quote.status && Objects.equals(insurancePriceTotal, quote.insurancePriceTotal) && Objects.equals(insurancePriceParcel, quote.insurancePriceParcel) && Objects.equals(creditContracts, quote.creditContracts) && Objects.equals(dateQuote, quote.dateQuote) && Objects.equals(customerId, quote.customerId) && Objects.equals(birthDateCustomer, quote.birthDateCustomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quoteId,
                productCode,
                status,
                insurancePriceTotal,
                insurancePriceParcel,
                creditContracts,
                dateQuote,
                customerId,
                birthDateCustomer);
    }

    public void sendContractForCaculation(CommandBroker command){
        command.sendCommand(this.creditContracts);
    }
}
