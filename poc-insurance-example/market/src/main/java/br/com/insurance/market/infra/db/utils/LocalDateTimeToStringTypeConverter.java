package br.com.insurance.market.infra.db.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;

public class LocalDateTimeToStringTypeConverter implements DynamoDBTypeConverter<String, LocalDate> {

    @Override
    public String convert(LocalDate localDateTime) {
        return localDateTime.toString();
    }

    @Override
    public LocalDate unconvert(String s) {
        return LocalDate.parse(s);
    }
}
