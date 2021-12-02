package br.com.insurance.calculation.engine.infra.db.spring.repositories;

import br.com.insurance.calculation.engine.domain.entity.TermFeeId;
import br.com.insurance.calculation.engine.domain.entity.TermFeeTax;
import br.com.insurance.calculation.engine.domain.repository.TermFeeTaxRespository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class CustomTermFeeRepository implements TermFeeTaxRespository {

    @Autowired
    private DynamoDBTemplate dynamoDBTemplate;

    @Override
    public PaginatedQueryList<TermFeeTax> getTermFee(TermFeeId partitionKey) {

        String key = partitionKey.productCode + "#" + partitionKey.getTimeDays();


        Map<String, AttributeValue> params = new HashMap<>();
        params.put("param1", new AttributeValue().withS(key));

        DynamoDBQueryExpression<TermFeeTax> queryExpression = new DynamoDBQueryExpression<TermFeeTax>()
                .withKeyConditionExpression("Id = :param1").withExpressionAttributeValues(params);

        return dynamoDBTemplate.query(TermFeeTax.class, queryExpression);

    }
}
