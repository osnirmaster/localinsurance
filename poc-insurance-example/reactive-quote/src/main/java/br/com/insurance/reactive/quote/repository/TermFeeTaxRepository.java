package br.com.insurance.reactive.quote.repository;

import br.com.insurance.reactive.quote.model.Quote;
import br.com.insurance.reactive.quote.model.TermFeeTax;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.concurrent.CompletableFuture;

@Repository
public class TermFeeTaxRepository {

    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<TermFeeTax> termFeeTaxDynamoDbAsyncTable;

    public TermFeeTaxRepository(DynamoDbEnhancedAsyncClient enhancedAsyncClient) {
        this.enhancedAsyncClient = enhancedAsyncClient;
        this.termFeeTaxDynamoDbAsyncTable = enhancedAsyncClient.table(
                TermFeeTax.class.getSimpleName(),
                TableSchema.fromBean(TermFeeTax.class));
    }

    //READ
    public CompletableFuture<TermFeeTax> getTermFeeByID(String productCode, Integer timeDays) {
        return termFeeTaxDynamoDbAsyncTable.getItem(getKeyBuild(productCode, timeDays));
    }

    private Key getKeyBuild(String productCode, Integer timeDays) {
        return Key.builder()
                .partitionValue(productCode)
                .sortValue(timeDays)
                .build();
    }
}
