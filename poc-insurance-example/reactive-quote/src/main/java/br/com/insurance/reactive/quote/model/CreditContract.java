package br.com.insurance.reactive.quote.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.math.BigDecimal;

@DynamoDbBean
public class CreditContract {

    private String creditAgreementId;
    private String productCreditCode;
    private Integer creditParcelAmount;
    private BigDecimal creditPriceTotal;
    private BigDecimal creditPriceParcel;


    public String getCreditAgreementId() {
        return creditAgreementId;
    }

    public void setCreditAgreementId(String creditAgreementId) {
        this.creditAgreementId = creditAgreementId;
    }

    public String getProductCreditCode() {
        return productCreditCode;
    }

    public void setProductCreditCode(String productCreditCode) {
        this.productCreditCode = productCreditCode;
    }

    public Integer getCreditParcelAmount() {
        return creditParcelAmount;
    }

    public void setCreditParcelAmount(Integer creditParcelAmount) {
        this.creditParcelAmount = creditParcelAmount;
    }

    public BigDecimal getCreditPriceTotal() {
        return creditPriceTotal;
    }

    public void setCreditPriceTotal(BigDecimal creditPriceTotal) {
        this.creditPriceTotal = creditPriceTotal;
    }

    public BigDecimal getCreditPriceParcel() {
        return creditPriceParcel;
    }

    public void setCreditPriceParcel(BigDecimal creditPriceParcel) {
        this.creditPriceParcel = creditPriceParcel;
    }

}
