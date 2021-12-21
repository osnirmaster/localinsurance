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

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

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

    public void getLockItem(QuoteId id) throws InterruptedException, IOException {
        System.out.println("Init lock... " );

        Key key = Key.builder()
                .partitionValue(id.customerId)
                .sortValue(id.quoteId)
                .build();

        final boolean createHeartbeatBackgroundThread = false;
        //build the lock client
        long leaseDuration = 2L;
        final AmazonDynamoDBLockClient client = new AmazonDynamoDBLockClient(
                AmazonDynamoDBLockClientOptions.builder(amazonDynamoDB, "QuoteLock")
                        .withTimeUnit(TimeUnit.SECONDS)
                        .withLeaseDuration(leaseDuration)
                        .withHeartbeatPeriod(2L)
                        .withCreateHeartbeatBackgroundThread(createHeartbeatBackgroundThread)
                        .build());

        while (true) {
            //try to acquire a lock on the partition key - cakeKey
            try {
                System.out.println("Attempt to acquire lock by " );
                final Optional<LockItem> lockItem = Optional.ofNullable(
                        client.acquireLock(AcquireLockOptions.builder(key.toString()).build()));
                if (lockItem.isPresent()) {
                    System.out.println("Acquired lock by " );

                    Quote quote = findById(id);

                    if (quote != null) {
                            System.out.println( " Quote avaliable: " + quote.getQuoteId());
                        try {
                            save(quote); // processing complete
                            System.out.println("Quote saved . " + "stopping");
                            break;

                        } catch (Exception e) {
                            System.err.println("Quote not saved with "  + "failed. " + e.getMessage());
                        }
                    }

                    client.releaseLock(lockItem.get()); //lock released
                    System.out.println(" taking a break");
                    Thread.sleep(leaseDuration * 3);
                } else {
                    System.out.println(" failed to acquire lock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
