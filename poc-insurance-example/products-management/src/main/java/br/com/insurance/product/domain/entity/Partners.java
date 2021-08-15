package br.com.insurance.product.domain.entity;

public class Partners {

    private String partnerName;
    private String description;
    private String paymentsMethod;
    private String callbackUrl;

    public Partners(String partnerName, String description, String paymentsMethod, String callbackUrl) {
        this.partnerName = partnerName;
        this.description = description;
        this.paymentsMethod = paymentsMethod;
        this.callbackUrl = callbackUrl;
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
