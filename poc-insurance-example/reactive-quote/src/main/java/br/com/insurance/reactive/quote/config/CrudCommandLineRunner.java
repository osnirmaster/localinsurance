package br.com.insurance.reactive.quote.config;


import br.com.insurance.reactive.quote.model.Quote;
import br.com.insurance.reactive.quote.model.TermFeeTax;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CrudCommandLineRunner implements CommandLineRunner {
    private final DynamoDbAsyncClient asyncClient;
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;


    public CrudCommandLineRunner(DynamoDbAsyncClient asyncClient, DynamoDbEnhancedAsyncClient enhancedAsyncClient) {
        this.asyncClient = asyncClient;
        this.enhancedAsyncClient = enhancedAsyncClient;
    }

    @Override
    public void run(String... args) {
        CompletableFuture<ListTablesResponse> listTablesResponseCompletableFuture = asyncClient.listTables();
        CompletableFuture<List<String>> listCompletableFuture = listTablesResponseCompletableFuture.thenApply(ListTablesResponse::tableNames);
        listCompletableFuture.thenAccept(tables -> {
            if (null != tables && !tables.contains(Quote.class.getSimpleName())) {
                DynamoDbAsyncTable<Quote> quote = enhancedAsyncClient.table(Quote.class.getSimpleName(), TableSchema.fromBean(Quote.class));
                quote.createTable();
            }

            if (null != tables && !tables.contains(TermFeeTax.class.getSimpleName())) {
                DynamoDbAsyncTable<TermFeeTax> tax = enhancedAsyncClient.table(TermFeeTax.class.getSimpleName(), TableSchema.fromBean(TermFeeTax.class));
                tax.createTable();
            }

        });
    }
}