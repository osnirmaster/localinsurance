package com.br.zero.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@DynamoDBTable(tableName = "apolices")
public class Apolice {

    @Id
    private ApoliceKey id;
    @DynamoDBAttribute
    private String racfCorretor;
    @DynamoDBAttribute
    private BigDecimal preco;


    @DynamoDBHashKey(attributeName = "apoliceId")
    public String getApoliceId() {
        return this.id != null ? this.id.getApoliceId() : null;
    }

    public void setApoliceId(String apoliceId) {
        if(this.id == null){
            this.id = new ApoliceKey();
        }

        this.id.setApoliceId(apoliceId);
    }

    @DynamoDBRangeKey(attributeName = "pessoaId")
    public String getPessoaId() {
        return this.id != null ? this.id.getPessoaId(): null;
    }

    public void setPessoaId(String pessoaId) {
        if(this.id == null){
            this.id = new ApoliceKey();
        }

        this.id.setPessoaId(pessoaId);
    }

    public String getRacfCorretor() {
        return racfCorretor;
    }

    public void setRacfCorretor(String racfCorretor) {
        this.racfCorretor = racfCorretor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Apolice{" +
                "id=" + id +
                ", racfCorretor='" + racfCorretor + '\'' +
                ", preco=" + preco +
                '}';
    }
}
