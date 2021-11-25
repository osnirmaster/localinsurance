package br.com.insurance.market.domain;

import br.com.insurance.market.domain.vo.QuoteStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private String productId;
    @Enumerated(EnumType.STRING)
    private QuoteStatus status = QuoteStatus.PENDENT;
    private BigDecimal insurancePriceTotal;
    private BigDecimal insurancePriceParcel;
    private Integer creditAgreementId;
    private BigDecimal creditPriceTotal;
    private BigDecimal creditPriceParcel;
    private Integer creditParcelAmount;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateQuote;
    private Integer customerId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDateCustomer;

    public Quote(UUID quoteId, String productId, QuoteStatus status, BigDecimal insurancePriceTotal, BigDecimal insurancePriceParcel, Integer creditAgreementId, BigDecimal creditPriceTotal, BigDecimal creditPriceParcel, Integer creditParcelAmount, LocalDate dateQuote, Integer customerId, LocalDate birthDateCustomer) {
        this.quoteId = quoteId;
        this.productId = productId;
        this.status = status;
        this.insurancePriceTotal = insurancePriceTotal;
        this.insurancePriceParcel = insurancePriceParcel;
        this.creditAgreementId = creditAgreementId;
        this.creditPriceTotal = creditPriceTotal;
        this.creditPriceParcel = creditPriceParcel;
        this.creditParcelAmount = creditParcelAmount;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Integer getCreditAgreementId() {
        return creditAgreementId;
    }

    public void setCreditAgreementId(Integer creditAgreementId) {
        this.creditAgreementId = creditAgreementId;
    }

    public BigDecimal getCreditPriceTotal() {
        return creditPriceTotal;
    }

    public void setCreditPriceTotal(BigDecimal creditPriceTotal) {
        this.creditPriceTotal = creditPriceTotal;
    }

    public BigDecimal getCreditPriceParcel() {
        return creditPriceParcel;
    }

    public void setCreditPriceParcel(BigDecimal creditPriceParcel) {
        this.creditPriceParcel = creditPriceParcel;
    }

    public Integer getCreditParcelAmount() {
        return creditParcelAmount;
    }

    public void setCreditParcelAmount(Integer creditParcelAmount) {
        this.creditParcelAmount = creditParcelAmount;
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
        return quoteId.equals(quote.quoteId) && Objects.equals(productId, quote.productId) && status == quote.status && Objects.equals(insurancePriceTotal, quote.insurancePriceTotal) && Objects.equals(insurancePriceParcel, quote.insurancePriceParcel) && Objects.equals(creditAgreementId, quote.creditAgreementId) && Objects.equals(creditPriceTotal, quote.creditPriceTotal) && Objects.equals(creditPriceParcel, quote.creditPriceParcel) && Objects.equals(creditParcelAmount, quote.creditParcelAmount) && Objects.equals(dateQuote, quote.dateQuote) && Objects.equals(customerId, quote.customerId) && Objects.equals(birthDateCustomer, quote.birthDateCustomer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quoteId, productId, status, insurancePriceTotal, insurancePriceParcel, creditAgreementId, creditPriceTotal, creditPriceParcel, creditParcelAmount, dateQuote, customerId, birthDateCustomer);
    }
}
