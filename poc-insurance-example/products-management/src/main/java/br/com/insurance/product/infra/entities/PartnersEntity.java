package br.com.insurance.product.infra.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Partner")
@Table(name = "partner")
public class PartnersEntity {

    @Id
    @GeneratedValue
    private UUID partnerId;
    private String partnerName;
    private String description;
    private String paymentsMethod;
    private String callbackUrl;

    public PartnersEntity(){}

    public PartnersEntity( UUID partnerId,String partnerName, String description, String paymentsMethod, String callbackUrl) {
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.description = description;
        this.paymentsMethod = paymentsMethod;
        this.callbackUrl = callbackUrl;
    }

    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentsMethod() {
        return paymentsMethod;
    }

    public void setPaymentsMethod(String paymentsMethod) {
        this.paymentsMethod = paymentsMethod;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartnersEntity that = (PartnersEntity) o;
        return Objects.equals(partnerId, that.partnerId) && Objects.equals(partnerName, that.partnerName) && Objects.equals(description, that.description) && Objects.equals(paymentsMethod, that.paymentsMethod) && Objects.equals(callbackUrl, that.callbackUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerId, partnerName, description, paymentsMethod, callbackUrl);
    }
}
