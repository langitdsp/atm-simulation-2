package com.mitrais.cdc.service;

import com.mitrais.cdc.model.Journal;

import java.util.List;

public interface TransactionService {

    public void withdrawalTrx(String sourceAccountNumber, int amount, String description) throws Exception;

    public void transferTrx(String sourceAccountNumber, String destinationAccountNumber, int amount, String description) throws Exception;

    public List<Journal> getTransactionHistory(String accountNUmber, int count);
}
