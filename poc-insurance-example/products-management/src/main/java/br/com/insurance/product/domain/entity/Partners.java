package br.com.insurance.product.domain.entity;

import java.util.UUID;

public class Partners {

    private UUID partnerId;
    private String partnerName;
    private String description;
    private String paymentsMethod;
    private String callbackUrl;

    public Partners(UUID partnerId){
        this.partnerId = partnerId;
    }

    public Partners(UUID partnerId,String partnerName, String description, String paymentsMethod, String callbackUrl) {
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
}
