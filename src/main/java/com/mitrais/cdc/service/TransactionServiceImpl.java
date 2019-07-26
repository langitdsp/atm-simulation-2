package com.mitrais.cdc.service;

import com.mitrais.cdc.dao.AccountDao;
import com.mitrais.cdc.dao.AccountDaoImpl;
import com.mitrais.cdc.dao.TransactionDao;
import com.mitrais.cdc.dao.TransactionDaoImpl;
import com.mitrais.cdc.exception.AccountNotFoundException;
import com.mitrais.cdc.exception.InvalidAmountException;
import com.mitrais.cdc.exception.NotEnoughAmountException;
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
    public void withdrawalTrx(String sourceAccountNumber, int amount, String description) throws Exception {

        if(amount > 1000 || amount%10 != 0){
            throw new InvalidAmountException();
        }

        Account account = this.accountDao.getUserByAccountNumber(sourceAccountNumber);

        if(account.getBalance() < amount){
            throw new NotEnoughAmountException();
        }

        Journal trx = new Journal();
        trx.setSourceAccountNumber(sourceAccountNumber);
        trx.setAmount(amount);
        trx.setType(Journal.TransType.DB.toString());
        trx.setDescription(description);

        transactionDao.createTrx(trx);
    }

    @Override
    public void transferTrx(String sourceAccountNumber, String destinationAccountNumber, int amount, String description) throws Exception {
        Account sourceAccount = this.accountDao.getUserByAccountNumber(sourceAccountNumber);
        Account destAccount = this.accountDao.getUserByAccountNumber(destinationAccountNumber);

        if(sourceAccount.getBalance() < amount){
            throw new NotEnoughAmountException();
        }

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
