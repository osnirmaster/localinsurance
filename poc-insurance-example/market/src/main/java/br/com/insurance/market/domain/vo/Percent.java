package br.com.insurance.market.domain.vo;


import java.math.BigDecimal;
import java.math.RoundingMode;


public class Percent {
    private BigDecimal value;
    
    
    public Percent(BigDecimal value) {
    	this.value = value;
    }

    public static Percent of(BigDecimal value) {
        return new Percent(value);
    }

    public MonetaryAmount multiply(MonetaryAmount amount) {
        return new MonetaryAmount(amount.getAmount().multiply(toValue()));
    }

    private BigDecimal toValue() {
        return value.divide(new BigDecimal("100"), 9, RoundingMode.HALF_UP);
    }
}
