package com.mitrais.cdc.screen;

import com.mitrais.cdc.dao.AccountDao;
import com.mitrais.cdc.dao.AccountDaoImpl;
import com.mitrais.cdc.exception.StopLoopException;
import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.utils.Utils;

import java.util.Scanner;

public class FundTransferScreen {
    final int maxTransferAmount = 1000;
    final int minTransferAmount = 1;
    private Account account;
    private AccountDao accountDao;

    public FundTransferScreen(Account account) {
        this.account = account;
        this.accountDao = new AccountDaoImpl();
    }

    public void start() throws StopLoopException {
        String destinationAccountNumber = "";
        String amount = "";
        String referenceNo = "";
        String option;

        System.out.println("\n\nFund Transfer...");

        System.out.println("Please enter destination account and");
        System.out.println("press enter to continue or");
        System.out.println("press cancel (Esc) to go back to Transaction : ");

        destinationAccountNumber = Utils.getKeyboardValue();

        System.out.println("Please enter transfer amount and");
        System.out.println("press enter to continue or");
        System.out.println("press cancel (Esc) to go back to Transaction : ");

        amount = Utils.getKeyboardValue();

        System.out.println("Please enter reference number (Optional) and");
        System.out.println("press enter to continue or");
        System.out.println("press cancel (Esc) to go back to Transaction : ");

        referenceNo = Utils.getKeyboardValue();

        // Transfer confirmation
        System.out.println("\n\nTransfer Confirmation");
        System.out.printf("%-20s : %s %n", "Destination Account", destinationAccountNumber);
        System.out.printf("%-20s : %s %n", "Transfer amount", amount);
        System.out.printf("%-20s : %s %n", "Reference Number", referenceNo);

        System.out.println("\n1. Confirm Trx");
        System.out.println("2. Cancel Trx");
        System.out.println("Choose option[2]");

        option = Utils.getKeyboardValue();

        switch (option){
            case "1":
                confirmTrx(destinationAccountNumber, amount, referenceNo);
                break;
            case "2":
                break;
            default:
                break;
        }

    }

    private void confirmTrx(String destinationAccountNumber, String amount, String referenceNo) throws StopLoopException {
        FundTransferSummaryScreen fundTransferSummaryScreen;

        int transferAmount = 0;
        // Check destinationAccountNumber format
        if (!Utils.isNumericOnlyValid(destinationAccountNumber)) {
            System.out.println("Invalid account");
            return;
        }

        // find destination account
        Account destinationAccount = this.accountDao.getUserByAccountNumber(destinationAccountNumber);
        if (destinationAccount == null){
            System.out.println("Invalid Account");
            return;
        }

        // Check amount to be a number
        if(!Utils.isNumericOnlyValid(amount)){
            System.out.println("Invalid Amount");
            return;
        }

        transferAmount = Integer.parseInt(amount);

        // Check maximum amount
        if(transferAmount > maxTransferAmount){
            System.out.println("Maximum amount to withdraw is $1000");
            return;
        }

        // Check minimum amount
        if(transferAmount < minTransferAmount){
            System.out.println("Minimum amount to withdraw is $1");
            return;
        }

        if(account.getBalance() < transferAmount){
            System.out.println("Insufficient balance $" + transferAmount);
            return;
        }

        if(!referenceNo.isEmpty() && !Utils.isNumericOnlyValid(referenceNo)){
            System.out.println("Invalid Reference Number");
            return;
        }

        this.account.setBalance(this.account.getBalance() - transferAmount);
        fundTransferSummaryScreen = new FundTransferSummaryScreen(this.account);
        fundTransferSummaryScreen.start(destinationAccountNumber, transferAmount, referenceNo);
    }
}
