package br.com.insurance.market.domain;

import br.com.insurance.market.domain.service.CommandBroker;
import br.com.insurance.market.domain.vo.QuoteStatus;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@DynamoDbBean
public class Quote {
    private String PK;
    private String SK;
    private QuoteId id;
    private String productCode;
    private QuoteStatus status = QuoteStatus.PENDENT;
    private List<CreditContract> creditContracts;
    private String dateQuote;
    private String segmentCustomerCode;
    private String birthDateCustomer;
    private Double coverTax;
    private Long version;

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

    public Quote(QuoteId quoteId, String productCode) {
        this.id = quoteId;
        this.productCode = productCode;
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

    public String getCustomerId() {
        return this.id != null ? this.id.getCustomerId() : null;
    }

    public void setCustomerId(String customerId) {
        if(this.id == null){
            this.id = new QuoteId();
        }

        this.id.setCustomerId(customerId);
    }

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


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getPK() {
        return this.id != null ? "CUSTOMER#" + this.id.getCustomerId() : null;
    }

    public void setPK(String PK) {
        if(this.id == null){
            this.id = new QuoteId();
        }

        this.id.setCustomerId(PK);
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getSK() {
        return this.id != null ? "QUOTE#" + this.id.getQuoteId() : null;
    }

    public void setSK(String SK) {
        if(this.id == null){
            this.id = new QuoteId();
        }

        this.id.setQuoteId(SK);
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
                '}';
    }
}
