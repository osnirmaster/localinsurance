package br.com.insurance.market.domain;

import br.com.insurance.market.domain.service.CommandBroker;
import br.com.insurance.market.domain.vo.QuoteStatus;
import br.com.insurance.market.infra.db.QuoteId;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@DynamoDBTable(tableName = "Quote")
public class Quote {
/*    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "quote_id", updatable = false, nullable = false)
    @org.hibernate.annotations.Type(type="pg-uuid")*/

    @Id
    private QuoteId id;

    private Integer customerId;

    private UUID quoteId;
//    @DynamoDBAttribute
    private String productCode;

//    @DynamoDBTypeConvertedEnum
    private QuoteStatus status = QuoteStatus.PENDENT;
//    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private BigDecimal insurancePriceTotal;
//    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private BigDecimal insurancePriceParcel;
//    @DynamoDBTypeConvertedJson
    private List<CreditContract> creditContracts;
//    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private LocalDate dateQuote;
//    @DynamoDBAttribute
    private String segmentCustomerCode;
//    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    private LocalDate birthDateCustomer;

    public Quote(){}

    public Quote(
                 QuoteId id,
                 String productCode,
                 LocalDate birthDateCustomer,
                 Integer customerId,
                 String segmentCustomerCode,
                 List<CreditContract> creditContracts
                 ) {
        this.id = id;
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

    @DynamoDBRangeKey(attributeName = "quoteId")
    public UUID getQuoteId() {
        return id.getQuoteId();
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

    @DynamoDBHashKey(attributeName = "customerId")
    public Integer getCustomerId() {
        return id.getCustomerId();
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

    public QuoteId getId() {
        return id;
    }

    public void setId(QuoteId id) {
        this.id = id;
    }


    public List<CreditContract> getCreditContracts() {
        return creditContracts;
    }

    public void setCreditContracts(List<CreditContract> creditContracts) {
        this.creditContracts = creditContracts;
    }

    public String getSegmentCustomerCode() {
        return segmentCustomerCode;
    }

    public void setSegmentCustomerCode(String segmentCustomerCode) {
        this.segmentCustomerCode = segmentCustomerCode;
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

    public void sendContractForCaculation(CommandBroker command) throws ExecutionException, InterruptedException {
        command.sendCommand(this.creditContracts);
    }
}
