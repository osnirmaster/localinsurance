package br.com.insurance.calculation.engine.infra.db.spring.repositories;

import br.com.insurance.calculation.engine.domain.entity.TermFeeId;
import br.com.insurance.calculation.engine.domain.entity.TermFeeTax;
import br.com.insurance.calculation.engine.domain.repository.TermFeeTaxRespository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomTermFeeRepository implements TermFeeTaxRespository {

    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient ;

    @Override
    public Optional<TermFeeTax> getTermFee(TermFeeId partitionKey) {

        DynamoDbTable<TermFeeTax> taxTable = getTable();
        // Construct the key with partition and sort key
        Key key = Key.builder().partitionValue(partitionKey.productCode)
                .sortValue(partitionKey.getTimeDays())
                .build();
        Optional <TermFeeTax> tax = Optional.of(taxTable.getItem(key)) ;

        return tax;
    }

    public PageIterable<TermFeeTax> queryTax(final TermFeeId id) {
        DynamoDbTable<TermFeeTax> taxTable = getTable();
        QueryEnhancedRequest query = QueryEnhancedRequest.builder().build();
        return taxTable.scan();
    }

    private DynamoDbTable<TermFeeTax> getTable() {
        // Create a tablescheme to scan our bean class order
        DynamoDbTable<TermFeeTax> taxDynamoDbTable =
                dynamoDbenhancedClient.table("TermFeeTax",
                        TableSchema.fromBean(TermFeeTax.class));
        return taxDynamoDbTable;
    }
}
