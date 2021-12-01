package br.com.insurance.market.domain;

import br.com.insurance.market.domain.service.CommandBroker;
import br.com.insurance.market.domain.vo.QuoteStatus;
import br.com.insurance.market.infra.db.QuoteId;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@DynamoDBTable(tableName = "Quote")
public class Quote {
    @Id
    private QuoteId id;
    @DynamoDBAttribute
    private String productCode;
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    private QuoteStatus status = QuoteStatus.PENDENT;
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private List<CreditContract> creditContracts;
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private LocalDate dateQuote;
    @DynamoDBAttribute
    private String segmentCustomerCode;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    @DynamoDBAttribute
    private LocalDate birthDateCustomer;
    @DynamoDBAttribute
    private Double coverTax;
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private CreditContractParcel creditContractParcel;

    public Quote(){}

    public Quote(
                 QuoteId id,
                 String productCode,
                 LocalDate birthDateCustomer,
                 String segmentCustomerCode,
                 List<CreditContract> creditContracts
                 ) {
        this.id = id;
        this.productCode = productCode;
        this.creditContracts = creditContracts;
        this.segmentCustomerCode = segmentCustomerCode;
    }

    public Quote(String productCode,
                 List<CreditContract> creditContracts,
                 LocalDate dateQuote,
                 LocalDate birthDateCustomer) {
        this.productCode = productCode;
        this.creditContracts = creditContracts;
        this.dateQuote = dateQuote;
        this.birthDateCustomer = birthDateCustomer;
    }

    public Quote(QuoteId quoteId, String productCode, CreditContractParcel parcels) {
        this.id = quoteId;
        this.productCode = productCode;
        this.creditContractParcel = parcels;
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

    public LocalDate getDateQuote() {
        return dateQuote;
    }

    public void setDateQuote(LocalDate dateQuote) {
        this.dateQuote = dateQuote;
    }

    @DynamoDBHashKey(attributeName = "customerId")
    public String getCustomerId() {
        return this.id != null ? this.id.getCustomerId() : null;
    }

    public void setCustomerId(String customerId) {
        if(this.id == null){
            this.id = new QuoteId();
        }

        this.id.setCustomerId(customerId);
    }

    @DynamoDBRangeKey(attributeName = "quoteId")
    public String getQuoteId() {
        return this.id != null ? this.id.getQuoteId() : null;
    }

    public void setQuoteId(String quoteId) {
        if(this.id == null){
            this.id = new QuoteId();
        }

        this.id.setQuoteId(quoteId);
    }

    public LocalDate getBirthDateCustomer() {
        return birthDateCustomer;
    }

    public void setBirthDateCustomer(LocalDate birthDateCustomer) {
        this.birthDateCustomer = birthDateCustomer;
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

    public Double getCoverTax() {
        return coverTax;
    }

    public void addCoverTax(Double coverTax) {
        this.coverTax = coverTax;
    }

    public CreditContractParcel getCreditContractParcel() {
        return creditContractParcel;
    }

    public void setCreditContractParcel(CreditContractParcel creditContractParcel) {
        this.creditContractParcel = creditContractParcel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(id, quote.id) && Objects.equals(productCode, quote.productCode) && status == quote.status && Objects.equals(creditContracts, quote.creditContracts) && Objects.equals(dateQuote, quote.dateQuote) && Objects.equals(segmentCustomerCode, quote.segmentCustomerCode) && Objects.equals(birthDateCustomer, quote.birthDateCustomer) && Objects.equals(coverTax, quote.coverTax) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                productCode,
                status,
                creditContracts,
                dateQuote,
                birthDateCustomer);
    }

    public void sendContractForCaculation(CommandBroker command) throws ExecutionException, InterruptedException {
        command.sendCommand(this);
    }
}
