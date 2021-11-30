package br.com.insurance.calculation.engine.domain.entity;

import java.math.BigDecimal;

public class Parcel {

    private Integer amount;
    private BigDecimal value;


    public Parcel(Integer amount, BigDecimal value) {
        this.amount = amount;
        this.value = value;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
