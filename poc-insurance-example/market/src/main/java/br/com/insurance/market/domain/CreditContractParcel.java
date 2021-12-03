package br.com.insurance.market.domain;

import br.com.insurance.market.domain.Parcel;

import java.util.List;

public class CreditContractParcel {
    private String creditAgreementId;
    private List<Parcel> parcels;

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
