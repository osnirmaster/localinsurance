package br.com.insurance.calculation.engine.domain.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import java.time.LocalDate;

public class TermFeeTax {

    public TermFeeId termFeeId;
    public Double tax;

    @DynamoDBHashKey(attributeName = "productCode")
    public String getProductCode() {
        return this.termFeeId != null ? this.termFeeId.getProductCode() : null;
    }

    public void setProductCode(String productCode) {
        if(this.termFeeId == null){
            this.termFeeId = new TermFeeId();
        }

        this.termFeeId.setProductCode(productCode);
    }

    @DynamoDBRangeKey(attributeName = "timeDays")
    public Integer getTimeDays() {
        return this.termFeeId != null ? this.termFeeId.getTimeDays() : null;
    }

    public void setTimeDays(Integer timeDays) {
        if(this.termFeeId == null){
            this.termFeeId = new TermFeeId();
        }

        this.termFeeId.setTimeDays(timeDays);
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTermFee(InsuranceCalculate insuranceCalculate){

        insuranceCalculate.getCreditContract().getCreditParcelAmount() * 30
    }
}
