package com.mitrais.cdc.screen;

import com.mitrais.cdc.exception.StopLoopException;
import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.utils.Utils;

import java.util.Scanner;

public class FundTransferSummaryScreen {
    private Account account;

    public FundTransferSummaryScreen(Account account) {
        this.account = account;
    }

    public void start(String destinationAccountNumber, int amount, String referenceNo) throws StopLoopException {
        String option;

        System.out.println("\n\nFund Transfer Summary");
        System.out.printf("%-20s : %s %n", "Destination Account", destinationAccountNumber);
        System.out.printf("%-20s : %d %n", "Transfer amount", amount);
        System.out.printf("%-20s : %s %n", "Reference Number", referenceNo);
        System.out.printf("%-20s : %s %n", "Balance", this.account.getBalance());


        System.out.println("\n1. Transaction");
        System.out.println("2. Exit");
        System.out.println("Choose option[2]");

        option = Utils.getKeyboardValue();

        switch (option) {
            case "1":
                break;
            case "2":
                throw new StopLoopException();
            default:
                break;
        }
    }
}
