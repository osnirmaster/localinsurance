package br.com.insurance.market.domain;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@DynamoDbBean
public class CreditContractParcel {
    private String quoteId;
    private String creditAgreementId;
    private List<Parcel> parcels;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getCreditAgreementId() {
        return creditAgreementId;
    }

    public void setCreditAgreementId(String creditAgreementId) {
        this.creditAgreementId = creditAgreementId;
    }

    @JsonIncludeProperties
    @DynamoDbAttribute("parcels")
    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public CreditContractParcel convertKeys(){
        setQuoteId("QUOTE#" + getQuoteId());
        setCreditAgreementId("CONTRACT#" + getCreditAgreementId());

        return  this;
    }

    @Override
    public String toString() {
        return "CreditContractParcel{" +
                "creditAgreementId='" + creditAgreementId + '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
