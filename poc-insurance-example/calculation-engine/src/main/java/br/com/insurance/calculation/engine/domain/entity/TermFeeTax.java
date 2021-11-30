package br.com.insurance.calculation.engine.domain.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@DynamoDBTable(tableName = "Tax")
public class TermFeeTax {
    @Id
    public TermFeeId termFeeId;
    @DynamoDBAttribute
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
