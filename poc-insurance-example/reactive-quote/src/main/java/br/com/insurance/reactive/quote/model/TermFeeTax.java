package br.com.insurance.reactive.quote.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@DynamoDbBean
public class TermFeeTax {

    public String productCode;
    public Integer timeDays;
    public Double tax;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("productCode")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("timeDays")
    public Integer getTimeDays() {
        return timeDays;
    }

    public void setTimeDays(Integer timeDays) {
        this.timeDays = timeDays;
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
        return Objects.equals(productCode, that.productCode) && Objects.equals(timeDays, that.timeDays) && Objects.equals(tax, that.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, timeDays, tax);
    }

    public static Double getTermFee(List<TermFeeTax> termFees){

        Optional<TermFeeTax> optional = Optional.of(termFees.stream().sorted().findFirst().get());
        return optional.get().getTax();
    }

    public Integer addTax(Integer amount){
        return amount * 30;
    }

    @Override
    public String toString() {
        return "TermFeeTax{" +
                "productCode='" + productCode + '\'' +
                ", timeDays=" + timeDays +
                ", tax=" + tax +
                '}';
    }
}
