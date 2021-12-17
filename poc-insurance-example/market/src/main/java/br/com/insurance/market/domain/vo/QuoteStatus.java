package br.com.insurance.market.domain.vo;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public enum QuoteStatus {
    PENDENT,FINISHED,CANCELED;
}
