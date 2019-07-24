package com.mitrais.cdc;

import java.util.Scanner;

import com.mitrais.cdc.dao.AccountDao;
import com.mitrais.cdc.dao.AccountDaoImpl;
import com.mitrais.cdc.dao.TransactionDao;
import com.mitrais.cdc.dao.TransactionDaoImpl;
import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.model.Transaction;
import com.mitrais.cdc.screen.WelcomeScreen;

/**
 * Hello world!
 *
 */
public class Atm 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        AccountDao accounts = new AccountDaoImpl();
        TransactionDao trx = new TransactionDaoImpl();

        trx.createTransaction(new Transaction());

        WelcomeScreen welcomeScreen = new WelcomeScreen(scanner);

        while(true){
            welcomeScreen.start();
        }

//        scanner.close();
    }

}