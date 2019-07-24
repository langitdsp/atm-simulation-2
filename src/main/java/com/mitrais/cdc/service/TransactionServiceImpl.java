package com.mitrais.cdc.service;

import com.mitrais.cdc.dao.AccountDao;
import com.mitrais.cdc.dao.AccountDaoImpl;
import com.mitrais.cdc.dao.TransactionDao;
import com.mitrais.cdc.dao.TransactionDaoImpl;
import com.mitrais.cdc.exception.AccountNotFoundException;
import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.model.Journal;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    AccountDao accountDao;
    TransactionDao transactionDao;

    public void TranSactionServiceImpl (){
        this.transactionDao = new TransactionDaoImpl();
        this.accountDao = new AccountDaoImpl();
    }

    @Override
    public void saveWithdrawalTrx(String sourceAccountNumber, int amount, String description) {

        Journal trx = new Journal();
        trx.setSourceAccountNumber(sourceAccountNumber);
        trx.setAmount(amount);
        trx.setType(Journal.TransType.DB.toString());
        trx.setDescription(description);

        transactionDao.createTrx(trx);
    }

    @Override
    public void saveTransferTrx(String sourceAccountNumber, String destinationAccountNumber, int amount, String description) throws AccountNotFoundException {
        Account destAccount = this.accountDao.getUserByAccountNumber(destinationAccountNumber);

        if (destAccount == null) {
            throw new AccountNotFoundException();
        }

        transactionDao = new TransactionDaoImpl();

        // create debit log
        Journal debitTrx = new Journal();
        debitTrx.setSourceAccountNumber(sourceAccountNumber);
        debitTrx.setDestinationAccountNumber(destinationAccountNumber);
        debitTrx.setAmount(amount);
        debitTrx.setType(Journal.TransType.DB.toString());
        debitTrx.setDescription(description);

        transactionDao.createTrx(debitTrx);

        if(!destinationAccountNumber.isEmpty()){
            // create credit log
            Journal creditTrx = new Journal();
            creditTrx.setSourceAccountNumber(destinationAccountNumber);
            creditTrx.setDestinationAccountNumber(sourceAccountNumber);
            creditTrx.setAmount(amount);
            creditTrx.setType(Journal.TransType.CR.toString());
            creditTrx.setDescription(description);

            transactionDao.createTrx(creditTrx);
        }
    }

    public List<Journal> getTransactionHistory(String accountNumber, int count){
        List<Journal> history = this.transactionDao.getTransactionHistory(accountNumber, count);

        return history;
    }
}
