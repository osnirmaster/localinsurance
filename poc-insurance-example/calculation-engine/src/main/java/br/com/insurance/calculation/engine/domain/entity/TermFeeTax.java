package br.com.insurance.calculation.engine.domain.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@DynamoDbBean
public class TermFeeTax {
    public TermFeeId termFeeId;
    public String productCode;
    public Integer timeDays;
    public Double tax;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("productCode")
    public String getProductCode() {
        return this.termFeeId != null ? this.termFeeId.getProductCode() : null;
    }

    public void setProductCode(String productCode) {
        if(this.termFeeId == null){
            this.termFeeId = new TermFeeId();
        }

        this.termFeeId.setProductCode(productCode);
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("timeDays")
    public Integer getTimeDays() {
        return this.termFeeId != null ? this.termFeeId.getTimeDays() : null;
    }

    public void setTimeDays(Integer timeDays) {
        if(this.termFeeId == null){
            this.termFeeId = new TermFeeId();
        }

        this.termFeeId.setTimeDays(timeDays);
    }



    @DynamoDbAttribute("tax")
    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TermFeeTax that = (TermFeeTax) o;
        return Objects.equals(termFeeId, that.termFeeId) && Objects.equals(tax, that.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(termFeeId, tax);
    }

    public static Double getTermFee(List<TermFeeTax> termFees){

        Optional<TermFeeTax> optional = Optional.of(termFees.stream().sorted().findFirst().get());
        return optional.get().getTax();
    }

    public Integer addTax(Integer amount){
        return amount * 30;
    }
}
