package br.com.insurance.calculation.engine.infra.db.spring.repositories.config;

import br.com.insurance.calculation.engine.domain.entity.TermFeeTax;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

        if (!listCompletableFuture.isEmpty() && !listCompletableFuture.contains("TermFeeTax")){
            DynamoDbTable<TermFeeTax> tax = enhancedClient
                    .table(TermFeeTax.class.getSimpleName(), TableSchema.fromBean(TermFeeTax.class));
            tax.createTable();
        }
    }
}
