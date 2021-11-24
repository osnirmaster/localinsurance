package br.com.insurance.market.domain;

import br.com.insurance.market.domain.vo.QuoteStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
}
