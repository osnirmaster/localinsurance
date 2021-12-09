package br.com.insurance.reactive.quote.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@DynamoDbBean
public class Quote {

    public String customerId;
    public String quoteId;
    private String productCode;
    private QuoteStatus status = QuoteStatus.PENDENT;
    private List<CreditContract> creditContracts = new ArrayList<>();
    private String dateQuote = LocalDate.now().toString();
    private String segmentCustomerCode;
    private String birthDateCustomer;
    private Double coverTax;
    private List<CreditContractParcel> creditContractParcel = new ArrayList<>() ;

    public Quote(){}

    public Quote(
                 String customerId,
                 String quoteId,
                 String productCode,
                 String birthDateCustomer,
                 String segmentCustomerCode,
                 List<CreditContract> creditContracts
                 ) {
        this.customerId = customerId;
        this.quoteId = quoteId;
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

    public Quote(String customerId,
                 String quoteId,
                 String productCode,
                 CreditContractParcel parcels) {
        this.customerId = customerId;
        this.quoteId = quoteId;
        this.productCode = productCode;
        this.creditContractParcel.add(parcels);
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("quoteId")
    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    @DynamoDbAttribute("productCode")
    public String getproductCode() {
        return productCode;
    }

    public void setproductCode(String productCode) {
        this.productCode = productCode;
    }

    @DynamoDbAttribute("status")
    public QuoteStatus getStatus() {
        return status;
    }

    public void setStatus(QuoteStatus status) {
        this.status = status;
    }

    @DynamoDbAttribute("dateQuote")
    public String getDateQuote() {
        return dateQuote;
    }

    public void setDateQuote(String dateQuote) {
        this.dateQuote = dateQuote;
    }

    @DynamoDbAttribute("birthDateCustomer")
    public String getBirthDateCustomer() {
        return birthDateCustomer;
    }

    public void setBirthDateCustomer(String birthDateCustomer) {
        this.birthDateCustomer = birthDateCustomer;
    }

    @DynamoDbAttribute("creditContracts")
    public List<CreditContract> getCreditContracts() {
        return creditContracts;
    }

    public void setCreditContracts(List<CreditContract> creditContracts) {
        this.creditContracts = creditContracts;
    }

    @DynamoDbAttribute("segmentCustomerCode")
    public String getSegmentCustomerCode() {
        return segmentCustomerCode;
    }

    public void setSegmentCustomerCode(String segmentCustomerCode) {
        this.segmentCustomerCode = segmentCustomerCode;
    }

    @DynamoDbAttribute("coverTax")
    public Double getCoverTax() {
        return coverTax;
    }

    public void setCoverTax(Double coverTax) {
        this.coverTax = coverTax;
    }

    @DynamoDbAttribute("creditContractParcel")
    public List<CreditContractParcel> getCreditContractParcel() {
        return creditContractParcel;
    }

    public void setCreditContractParcel(List<CreditContractParcel> creditContractParcel) {
        this.creditContractParcel = creditContractParcel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(customerId, quote.customerId) && Objects.equals(quoteId, quote.quoteId) && Objects.equals(productCode, quote.productCode) && status == quote.status && Objects.equals(creditContracts, quote.creditContracts) && Objects.equals(dateQuote, quote.dateQuote) && Objects.equals(segmentCustomerCode, quote.segmentCustomerCode) && Objects.equals(birthDateCustomer, quote.birthDateCustomer) && Objects.equals(coverTax, quote.coverTax) && Objects.equals(creditContractParcel, quote.creditContractParcel);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + customerId + "#" + quoteId +
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
