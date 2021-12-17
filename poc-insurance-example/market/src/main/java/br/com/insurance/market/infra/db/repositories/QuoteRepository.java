package br.com.insurance.market.infra.db.repositories;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.Optional;

@Slf4j
@Repository
public class QuoteRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient ;

    public Quote save(final Quote quote) {
        DynamoDbTable<Quote> quoteTable = getTable();

        try {
            quoteTable.putItem(quote);
            return quote;
        } catch (DynamoDbException ex) {

            log.error("erro: {}", ex);
            return null;
        }
    }

    public Optional<Quote> findById(QuoteId id) {
        DynamoDbTable<Quote> quoteTable = getTable();
        Key key = Key.builder()
                .partitionValue(id.customerId)
                .sortValue(id.quoteId)
                .build();

        return Optional.of(quoteTable.getItem(key));
    }

    private DynamoDbTable<Quote> getTable() {
        // Create a tablescheme to scan our bean class quote
        DynamoDbTable<Quote> quoteTable =
                dynamoDbenhancedClient.table("Quote",
                        TableSchema.fromBean(Quote.class));
        return quoteTable;
    }

}
