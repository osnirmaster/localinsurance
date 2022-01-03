package br.com.insurance.reactive.quote.repository;

import br.com.insurance.reactive.quote.model.TermFeeTax;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.paginators.ListTablesPublisher;

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


    public PagePublisher<TermFeeTax> queryTax() {

            return termFeeTaxDynamoDbAsyncTable.scan();
    }

    private Key getKeyBuild(String productCode, Integer timeDays) {
        return Key.builder()
                .partitionValue(productCode)
                .sortValue(timeDays)
                .build();
    }
}
