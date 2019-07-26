package com.mitrais.cdc.screen;

import java.util.Scanner;

import com.mitrais.cdc.exception.StopLoopException;
import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.utils.Utils;

public class WithdrawScreen {
    private Account account;
    private SummaryScreen summaryScreen;

    public WithdrawScreen(Account account) {
        this.account = account;
        this.summaryScreen = new SummaryScreen(account);
    }

    public void start() throws StopLoopException  {
        String option;

        System.out.println("\n\nWithdraw");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.println("Please choose option[5]: ");

        option = Utils.getKeyboardValue();

        switch (option) {
            case "1":
                if (this.account.getBalance() < 10) {
                    System.out.println("Insufficient balance $" + this.account.getBalance());
                    break;
                }
                this.account.setBalance(this.account.getBalance() - 10);
                this.summaryScreen.start(10);
                break;
            case "2":
                if (this.account.getBalance() < 50) {
                    System.out.println("Insufficient balance $" + this.account.getBalance());
                    break;
                }

                this.account.setBalance(this.account.getBalance() - 50);
                this.summaryScreen.start(50);
                break;
            case "3":
                if (this.account.getBalance() < 100) {
                    System.out.println("Insufficient balance $" + this.account.getBalance());
                    break;
                }
                this.account.setBalance(this.account.getBalance() - 100);
                this.summaryScreen.start(100);
                break;
            case "4":
                otherWithdraw();
                break;
            case "5":
                break;
            default:
                break;

        }
    }

    public void otherWithdraw() throws StopLoopException {

        System.out.println("\n\nOther Withdraw");
        System.out.println("Enter amount to withdraw");

        String amount = Utils.getKeyboardValue();
        if(!Utils.isNumericOnlyValid(amount)){
            System.out.println("Invalid amount");
            return;
        }

        int amountInt = Integer.parseInt(amount);

        if (amountInt > 1000 || amountInt % 10 != 0) {
            System.out.println("Invalid amount");
        } else if (amountInt > this.account.getBalance()) {
            System.out.println("Insufficeint balance $" + amount);
        } else {
            this.account.setBalance(account.getBalance() - amountInt);
            this.summaryScreen.start(amountInt);
        }
    }
}