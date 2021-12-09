package br.com.insurance.reactive.quote.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.math.BigDecimal;

@DynamoDbBean
public class Parcel {

    private Integer amount;
    private BigDecimal value;

    public Parcel(){}

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

    @Override
    public String toString() {
        return "Parcel{" +
                "amount=" + amount +
                ", value=" + value +
                '}';
    }
}
