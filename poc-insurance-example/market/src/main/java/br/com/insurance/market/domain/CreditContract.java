package br.com.insurance.market.domain;

import java.math.BigDecimal;

public class CreditContract {

    private Integer creditAgreementId;
    private String productCreditCode;
    private Integer creditParcelAmount;
    private BigDecimal creditPriceTotal;
    private BigDecimal creditPriceParcel;

    public Integer getCreditAgreementId() {
        return creditAgreementId;
    }

    public void setCreditAgreementId(Integer creditAgreementId) {
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
