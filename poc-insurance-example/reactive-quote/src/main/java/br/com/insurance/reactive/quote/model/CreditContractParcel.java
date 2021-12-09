package br.com.insurance.reactive.quote.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class CreditContractParcel  {
    private String creditAgreementId;
    private List<Parcel> parcels;

    public CreditContractParcel(){}

    public CreditContractParcel(String creditAgreementId, List<Parcel> parcels) {
        this.creditAgreementId = creditAgreementId;
        this.parcels = parcels;
    }

    public String getCreditAgreementId() {
        return creditAgreementId;
    }

    public void setCreditAgreementId(String creditAgreementId) {
        this.creditAgreementId = creditAgreementId;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    @Override
    public String toString() {
        return "CreditContractParcel{" +
                "creditAgreementId='" + creditAgreementId + '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
