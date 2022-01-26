package com.br.zero.infra.db.repositories.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.br.zero.infra.db.repositories")
public class DynamodbConfig {

    @Autowired
    private Environment environment;

    private static final Logger LOGGER = LogManager.getLogger(DynamodbConfig.class);

    public AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "sa-east-1");
    }

    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials("123", "123"));
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        String env = environment.getProperty("app.aws.env");
        String region = Region.getRegion(Regions.SA_EAST_1).getName();
        String endpoint = environment.getProperty("app.aws.dynamodb.endpoint");

        AmazonDynamoDB amazonDynamoDB;
        if(env != null && env.equals("localstack")){
            LOGGER.info("Instanciando dynamodb com LocalStack");
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration( new AwsClientBuilder.EndpointConfiguration(endpoint, region)).build();
        } else{
            LOGGER.info("Instanciando na AWS");
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
        }

        return amazonDynamoDB;
    }



}
