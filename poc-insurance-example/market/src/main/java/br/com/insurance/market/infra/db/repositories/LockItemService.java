package br.com.insurance.market.infra.db.repositories;

import br.com.insurance.market.domain.Quote;
import br.com.insurance.market.domain.QuoteId;
import br.com.insurance.market.domain.vo.QuoteStatus;
import com.amazonaws.services.dynamodbv2.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LockItemService {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    @Autowired
    private QuoteRepository quoteRepository;

    public LockItem getLockItem(QuoteId id, Quote quoteUpdated) throws InterruptedException, IOException {

        System.out.println("Init lock... " );

        Key key = Key.builder()
                .partitionValue(id.customerId)
                .sortValue(id.quoteId)
                .build();

        final boolean createHeartbeatBackgroundThread = true;
        //build the lock client
        long leaseDuration = 5L;
        final AmazonDynamoDBLockClient client = new AmazonDynamoDBLockClient(
                AmazonDynamoDBLockClientOptions.builder(amazonDynamoDB, "QuoteLock")
                        .withTimeUnit(TimeUnit.SECONDS)
                        .withLeaseDuration(leaseDuration)
                        .withHeartbeatPeriod(30L)
                        .withCreateHeartbeatBackgroundThread(createHeartbeatBackgroundThread)
                        .build());


        while (true) {
            //try to acquire a lock on the partition key - cakeKey
            try {
                System.out.println("Attempt to acquire lock by " );
                final Optional<LockItem> lockItem = Optional.ofNullable(
                        client.acquireLock(AcquireLockOptions.builder(key.toString())
                                .withShouldSkipBlockingWait(false)
                                .build()));

                if (lockItem.isPresent()) {
                    System.out.println("Acquired lock by " );

                    Quote quote = quoteRepository.findById(id);
                    quote.getCreditContractParcel().add(quoteUpdated.getCreditContractParcel().get(0));

                    if (quote != null) {
                        System.out.println( " Quote avaliable: " + quote.getQuoteId());

                        Integer contractsNumber = Math.toIntExact(quote.getCreditContracts().stream().count());
                        Integer contractsUpdated = Math.toIntExact(quote.getCreditContractParcel().stream().count());

                        log.info("Contratos Enviados: {} vs {} Contratos Calculados", contractsNumber, contractsUpdated);
                        log.info("quote id: {}, credit id: {}", quoteUpdated.getQuoteId(), quoteUpdated.getCreditContractParcel().get(0).getCreditAgreementId() );

                        if (contractsUpdated.equals(contractsNumber) ){
                            quote.setStatus(QuoteStatus.FINISHED);
                            log.info("Calculo Quotacao finalizada", contractsNumber, contractsUpdated);
                        }

                        try {
                            quoteRepository.save(quote); // processing complete
                            System.out.println("Quote saved . " + "stopping");
                            break;

                        } catch (Exception e) {
                            System.err.println("Quote not saved with "  + "failed. " + e.getMessage());
                        }
                    }

                    client.releaseLock(lockItem.get()); //lock released
                    System.out.println(" taking a break");

                    return lockItem.get()
                            ;
                } else {
                    System.out.println(" failed to acquire lock");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
