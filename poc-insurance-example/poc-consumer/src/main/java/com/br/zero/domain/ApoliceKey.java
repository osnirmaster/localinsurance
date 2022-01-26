package com.br.zero.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import java.io.Serializable;

public class ApoliceKey implements Serializable {

    private String apoliceId;
    private String pessoaId;

    @DynamoDBHashKey(attributeName = "apoliceId")
    public String getApoliceId() {
        return apoliceId;
    }

    public void setApoliceId(String apoliceId) {
        this.apoliceId = apoliceId;
    }

    @DynamoDBRangeKey(attributeName = "pessoaId")
    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String pessoaId) {
        this.pessoaId = pessoaId;
    }
}
