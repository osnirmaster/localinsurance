package br.com.insurance.market.domain.service;

import br.com.insurance.market.domain.CreditContract;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface CommandBroker {

    void sendCommand(List<CreditContract> contractList) throws ExecutionException, InterruptedException;
}
