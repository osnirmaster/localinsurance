package br.com.insurance.calculation.engine.domain.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@DynamoDbBean
public class TermFeeId implements Serializable {

    private static final long serialVersionUID = 1L;

    public String productCode;
    public Integer timeDays;

    public TermFeeId(){}

    public TermFeeId(String productCode, Integer timeDays) {
        this.productCode = productCode;
        this.timeDays = timeDays;
    }

    @DynamoDBHashKey(attributeName = "productCode")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @DynamoDBRangeKey(attributeName = "timeDays")
    public Integer getTimeDays() {
        return timeDays;
    }

    public void setTimeDays(Integer timeDays) {
        this.timeDays = timeDays;
    }
}
