package br.com.insurance.reactive.quote.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration
public class DynamoDBConfig {
    private final String dynamoDbEndPointUrl;
    public DynamoDBConfig(@Value("${aws.dynamodb.endpoint}") String dynamoDbEndPointUrl) {
        this.dynamoDbEndPointUrl = dynamoDbEndPointUrl;
    }

    @Bean
    public DynamoDbAsyncClient getDynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .endpointOverride(URI.create(dynamoDbEndPointUrl))
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient() {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(getDynamoDbAsyncClient())
                .build();
    }

}
