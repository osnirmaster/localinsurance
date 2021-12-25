package br.com.insurance.calculation.engine.domain.entity;

import java.util.List;

public class CreditContractParcel {
    private String quoteId;
    private String creditAgreementId;
    private List<Parcel> parcels;

    public CreditContractParcel( String quoteId,String creditAgreementId, List<Parcel> parcels) {
        this.quoteId = quoteId;
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
                "quoteId='" + quoteId + '\'' +
                ", creditAgreementId='" + creditAgreementId + '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
