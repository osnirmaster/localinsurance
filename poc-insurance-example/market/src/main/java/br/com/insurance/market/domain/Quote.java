package br.com.insurance.market.domain;

import br.com.insurance.market.domain.service.CommandBroker;
import br.com.insurance.market.domain.vo.QuoteStatus;
import br.com.insurance.market.infra.db.QuoteId;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    @DynamoDBAttribute
    private String dateQuote;
    @DynamoDBAttribute
    private String segmentCustomerCode;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    @DynamoDBAttribute
    private String birthDateCustomer;
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.N)
    @DynamoDBAttribute
    private Double coverTax;
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private List<CreditContractParcel> creditContractParcel = new ArrayList<>() ;

    public Quote(){}

    public Quote(
                 QuoteId id,
                 String productCode,
                 String birthDateCustomer,
                 String segmentCustomerCode,
                 List<CreditContract> creditContracts
                 ) {
        this.id = id;
        this.productCode = productCode;
        this.creditContracts = creditContracts;
        this.segmentCustomerCode = segmentCustomerCode;
        this.dateQuote = LocalDate.now().toString();
    }

    public Quote(String productCode,
                 List<CreditContract> creditContracts,
                 String dateQuote,
                 String birthDateCustomer) {
        this.productCode = productCode;
        this.creditContracts = creditContracts;
        this.dateQuote = dateQuote;
        this.birthDateCustomer = birthDateCustomer;
    }

    public Quote(QuoteId quoteId, String productCode, CreditContractParcel parcels) {
        this.id = quoteId;
        this.productCode = productCode;
        this.creditContractParcel.add(parcels);
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

    public String getDateQuote() {
        return dateQuote;
    }

    public void setDateQuote(String dateQuote) {
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

    public String getBirthDateCustomer() {
        return birthDateCustomer;
    }

    public void setBirthDateCustomer(String birthDateCustomer) {
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

    public void setCoverTax(Double coverTax) {
        this.coverTax = coverTax;
    }

    public List<CreditContractParcel> getCreditContractParcel() {
        return creditContractParcel;
    }

    public void setCreditContractParcel(List<CreditContractParcel> creditContractParcel) {
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

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", status=" + status +
                ", creditContracts=" + creditContracts +
                ", dateQuote=" + dateQuote +
                ", segmentCustomerCode='" + segmentCustomerCode + '\'' +
                ", birthDateCustomer=" + birthDateCustomer +
                ", coverTax=" + coverTax +
                ", creditContractParcel=" + creditContractParcel +
                '}';
    }
}
