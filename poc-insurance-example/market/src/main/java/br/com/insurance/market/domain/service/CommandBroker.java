package br.com.insurance.market.domain.service;

import br.com.insurance.market.domain.CreditContract;
import br.com.insurance.market.domain.Quote;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface CommandBroker {

    void sendCommand(Quote quote) throws ExecutionException, InterruptedException;
}
