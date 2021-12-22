package br.com.insurance.market.infra.db.repositories;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import com.amazonaws.services.dynamodbv2.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.TransactGetItem;
import software.amazon.awssdk.services.dynamodb.model.TransactGetItemsRequest;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class QuoteRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient ;

    public Quote save(final Quote quote) {
        DynamoDbTable<Quote> quoteTable = getTable();
        quoteTable.putItem(quote);

        return quote;
    }

    public Quote findById(QuoteId id) {
        DynamoDbTable<Quote> quoteTable = getTable();

        Key key = Key.builder()
                .partitionValue(id.customerId)
                .sortValue(id.quoteId)
                .build();

        return quoteTable.getItem(key);
    }

    private DynamoDbTable<Quote> getTable() {
        // Create a tablescheme to scan our bean class quote
        DynamoDbTable<Quote> quoteTable =
                dynamoDbenhancedClient.table("Quote",
                        TableSchema.fromBean(Quote.class));
        return quoteTable;
    }

}
