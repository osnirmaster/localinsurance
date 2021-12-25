package br.com.insurance.market.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.ArrayList;
import java.util.List;

@DynamoDbBean
public class CreditContractParcel {
    private String PK;
    private String SK;
    private String quoteId;
    private String creditAgreementId;
    private List<Parcel> parcels = new ArrayList<>();

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getPK() {
        return "QUOTE#" + getQuoteId();
    }

    public void setPK(String PK) {
        this.PK = PK;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getSK() {
        return "CONTRACT#" + getCreditAgreementId();
    }

    public void setSK(String SK) {
        this.SK = SK;
    }


    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }


    public String getCreditAgreementId() {
        return creditAgreementId;
    }

    public void setCreditAgreementId(String creditAgreementId) {
        this.creditAgreementId = creditAgreementId;
    }

    @DynamoDBTypeConvertedJson
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
                "quoteId='" + quoteId + '\'' +
                ", creditAgreementId='" + creditAgreementId + '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
