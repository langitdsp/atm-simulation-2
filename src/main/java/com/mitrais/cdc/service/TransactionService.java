package com.mitrais.cdc.service;

import com.mitrais.cdc.exception.AccountNotFoundException;
import com.mitrais.cdc.model.Journal;

import java.util.List;

public interface TransactionService {

    public void saveWithdrawalTrx(String sourceAccountNumber, int amount, String description);

    public void saveTransferTrx(String sourceAccountNumber, String destinationAccountNumber, int amount, String description) throws AccountNotFoundException;

    public List<Journal> getTransactionHistory(String accountNUmber, int count);
}
