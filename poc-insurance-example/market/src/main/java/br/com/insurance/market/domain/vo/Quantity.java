package br.com.insurance.market.domain.vo;

import java.math.BigDecimal;

//teste
public class Quantity {

    private BigDecimal value;
    
    public Quantity(BigDecimal value) {
    	this.value = value;
    }

    public static Quantity of(BigDecimal value) {
        return new Quantity(value);
    }

    public static Quantity zero() {
        return new Quantity(BigDecimal.ZERO);
    }

    public MonetaryAmount multiply(MonetaryAmount amount) {
        return amount.multiply(value);
    }

    public static Quantity min(Quantity q1, Quantity q2) {
        return q1.value.compareTo(q2.value) >= 1 ? q2 : q1;
    }

    public static Quantity max(Quantity q1, Quantity q2) {
        return q1.value.compareTo(q2.value) >= 1 ? q1 : q2;
    }

    public Quantity add(Quantity qt){
        return new Quantity(qt.value.add(value));
    }
    
    public BigDecimal getValue() {
    	return this.value;
    }

    public Quantity subtract(Quantity qt) { return new Quantity(value.subtract(qt.value)); }
}
