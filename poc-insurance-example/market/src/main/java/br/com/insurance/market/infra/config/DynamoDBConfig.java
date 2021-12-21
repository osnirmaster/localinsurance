package br.com.insurance.market.infra.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import java.net.URI;
import java.util.concurrent.TimeUnit;


@Configuration
public class DynamoDBConfig {

    private final String dynamoDbEndPointUrl;
    private static final AwsClientBuilder.EndpointConfiguration DYNAMODB_LOCAL_ENDPOINT =
            new AwsClientBuilder.EndpointConfiguration("http://localhost:8000",
                    "sa-east-1");

    public DynamoDBConfig(@Value("${aws.dynamodb.endpoint}") String dynamoDbEndPointUrl) {
        this.dynamoDbEndPointUrl = dynamoDbEndPointUrl;
    }

    @Bean
    public DynamoDbClient getDynamoDbClient() {

        final AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(DYNAMODB_LOCAL_ENDPOINT)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

/*         AmazonDynamoDBLockClient.createLockTableInDynamoDB(
                CreateDynamoDBTableOptions.builder(amazonDynamoDB(),new ProvisionedThroughput(25L, 25L), "QuoteLock").build());*/

        return DynamoDbClient.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .endpointOverride(URI.create(dynamoDbEndPointUrl))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(getDynamoDbClient())
                .build();
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "sa-east-1"))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("123", "123")))
                .build();
    }


}
