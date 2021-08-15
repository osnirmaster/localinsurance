package br.com.insurance.product.infra.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Partner")
@Table(name = "partners")
public class PartnersEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idPartner;
    private String partnerName;
    private String description;
    private String paymentsMethod;
    private String callbackUrl;

    public PartnersEntity(){}

    public PartnersEntity(String partnerName, String description, String paymentsMethod, String callbackUrl) {
        this.partnerName = partnerName;
        this.description = description;
        this.paymentsMethod = paymentsMethod;
        this.callbackUrl = callbackUrl;
    }

    public UUID getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(UUID idPartner) {
        this.idPartner = idPartner;
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
        return Objects.equals(idPartner, that.idPartner) && Objects.equals(partnerName, that.partnerName) && Objects.equals(description, that.description) && Objects.equals(paymentsMethod, that.paymentsMethod) && Objects.equals(callbackUrl, that.callbackUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPartner, partnerName, description, paymentsMethod, callbackUrl);
    }
}
