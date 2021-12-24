package br.com.insurance.market.infra.db.repositories;

import br.com.insurance.market.domain.CreditContractParcel;
import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
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
        DynamoDbTable<Quote> quoteTable = getTableQuote();
        quoteTable.putItem(quote);

        return quote;
    }

    public Quote findById(QuoteId id) {
        DynamoDbTable<Quote> quoteTable = getTableQuote();

        Key key = Key.builder()
                .partitionValue(id.customerId)
                .sortValue(id.quoteId)
                .build();

        return quoteTable.getItem(key);
    }

    public CreditContractParcel saveParcel(final CreditContractParcel contract){
        log.info("entitiy: {}", contract);
        DynamoDbTable<CreditContractParcel> contractTable = getTableContract();
        contractTable.putItem(contract);

        return contract;
    }

    public PageIterable<CreditContractParcel> getContractParcel(CreditContractParcel contract){
        log.info("entitiy: {}", contract);
        DynamoDbTable<CreditContractParcel> contractTable = getTableContract();

        return contractTable
                .query(QueryConditional
                        .keyEqualTo(k -> k.partitionValue("QUOTE#"+ contract.getQuoteId())));
    }


    private DynamoDbTable<Quote> getTableQuote() {
        // Create a tablescheme to scan our bean class quote
        DynamoDbTable<Quote> quoteTable =
                dynamoDbenhancedClient.table("Quote",
                        TableSchema.fromBean(Quote.class));
        return quoteTable;
    }

    private DynamoDbTable<CreditContractParcel> getTableContract() {
        // Create a tablescheme to scan our bean class quote
        DynamoDbTable<CreditContractParcel> creditContractParcel =
                dynamoDbenhancedClient.table("Quote",
                        TableSchema.fromBean(CreditContractParcel.class));
        return creditContractParcel;
    }

}
