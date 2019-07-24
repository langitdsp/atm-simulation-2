package com.mitrais.cdc.dao;

import com.mitrais.cdc.model.Journal;

import java.util.List;

public interface TransactionDao {

    public void createTrx(Journal transaction);

    public List<Journal> getTransactionHistory(String accountNumber, int count);
}
