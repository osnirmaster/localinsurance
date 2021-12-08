package br.com.insurance.reactive.quote.repository;

import br.com.insurance.reactive.quote.model.Quote;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class QuoteRepository {

    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<Quote> quoteDynamoDbAsyncTable;

    public QuoteRepository(DynamoDbEnhancedAsyncClient enhancedAsyncClient){
        this.enhancedAsyncClient = enhancedAsyncClient;
        this.quoteDynamoDbAsyncTable = enhancedAsyncClient.table(
                Quote.class.getSimpleName(),
                TableSchema.fromBean(Quote.class));;
    }

    //CREATE
    public CompletableFuture<Void> save(Quote quote) {
        quote.setQuoteId(UUID.randomUUID().toString());
        return quoteDynamoDbAsyncTable.putItem(quote);
    }

    //READ
    public CompletableFuture<Quote> getQuoteByID(String primaryKey, String sortedKey) {
        return quoteDynamoDbAsyncTable.getItem(getKeyBuild(primaryKey, sortedKey));
    }

    private Key getKeyBuild(String primaryKey, String sortedKey) {
        return Key.builder()
                .partitionValue(primaryKey)
                .sortValue(sortedKey)
                .build();
    }
}
