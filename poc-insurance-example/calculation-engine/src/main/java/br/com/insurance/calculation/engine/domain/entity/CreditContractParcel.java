package br.com.insurance.calculation.engine.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class CreditContractParcel {
    private String quoteId;
    private String creditAgreementId;
    private List<Parcel> parcels = new ArrayList<>();

    public CreditContractParcel( String quoteId,String creditAgreementId, List<Parcel> parcels) {
        this.quoteId = quoteId;
        this.creditAgreementId = creditAgreementId;
        this.parcels = parcels;
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
