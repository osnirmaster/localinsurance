package br.com.insurance.market.infra.config;

import br.com.insurance.market.domain.Quote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
import java.util.List;

@Component
public class CrudCommandLineRunner implements CommandLineRunner {
    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient enhancedClient;

    public CrudCommandLineRunner(DynamoDbClient dynamoDbClient, DynamoDbEnhancedClient enhancedClient) {
        this.dynamoDbClient = dynamoDbClient;
        this.enhancedClient = enhancedClient;
    }

    @Override
    public void run(String... args) {
        ListTablesResponse listTablesResponseCompletableFuture = dynamoDbClient.listTables();
        List<String> listCompletableFuture = listTablesResponseCompletableFuture.tableNames();

        if (!listCompletableFuture.isEmpty() && !listCompletableFuture.contains("Quote")){
            DynamoDbTable<Quote> quote = enhancedClient.table(Quote.class.getSimpleName(), TableSchema.fromBean(Quote.class));
            quote.createTable();
        }
    }
}
