package com.mitrais.cdc.screen;

import java.util.Scanner;

import com.mitrais.cdc.dao.AccountDao;
import com.mitrais.cdc.dao.AccountDaoImpl;
import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.utils.Utils;

/*
 * Welcome screen
 */
public class WelcomeScreen {
    AccountDao accountDao;

    public WelcomeScreen() {
        this.accountDao = new AccountDaoImpl();
    }

    public void start() {
        String accountNumber = "";
        String pin = "";
        Account account = new Account();

        System.out.println("\n\nWelcome...");
        // Enter account number
        System.out.println("Enter Account Number");
        accountNumber = Utils.getKeyboardValue();

        if (!Utils.isLengthValid(accountNumber)) {
            System.out.println("Account Number should have 6 digits number");
            return;
        }

        if (!Utils.isNumericOnlyValid(accountNumber)) {
            System.out.println("Account Number should only contains numbers");
            return;
        }

        // Enter account number
        System.out.println("Enter PIN");
        pin = Utils.getKeyboardValue();

        if (!Utils.isLengthValid(pin)) {
            System.out.println("PIN should have 6 digits number");
            return;
        }

        if (!Utils.isNumericOnlyValid(pin)) {
            System.out.println("PIN should only contains numbers");
            return;
        }

        account = this.accountDao.loginUser(accountNumber, pin);

        if (account == null) {
            System.out.println("Invalid Account Number/PIN");
            return;
        }

        TransactionScreen transactionScreen = new TransactionScreen(account);
        transactionScreen.start();


    }
}