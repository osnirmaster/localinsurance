package br.com.insurance.product.domain.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Meta {

    private String groupName;
    private String branchName;
    private String productName;
    private String partnerName;

    public Meta(){

    }

    public Meta(String groupName, String branchName, String productName, String partnerName) {
        this.groupName = groupName;
        this.branchName = branchName;
        this.productName = productName;
        this.partnerName = partnerName;
    }
}
