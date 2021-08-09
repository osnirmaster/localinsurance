package br.com.insurance.product.domain.entity;

public class Partners {

    private Long id;
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

    public Partners(Long id, String partnerName, String description, String paymentsMethod, String callbackUrl) {
        this.id = id;
        this.partnerName = partnerName;
        this.description = description;
        this.paymentsMethod = paymentsMethod;
        this.callbackUrl = callbackUrl;
    }


}
