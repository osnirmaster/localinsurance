package br.com.insurance.reactive.quote.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;


public enum QuoteStatus {
    PENDENT,FINISHED,CANCELED;

    QuoteStatus() {
    }
}
