package com.mitrais.cdc.screen;

import com.mitrais.cdc.exception.StopLoopException;
import com.mitrais.cdc.model.Account;
import com.mitrais.cdc.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SummaryScreen {
    private Account account;
    private LocalDateTime dateNow;

    public SummaryScreen(Account account) {
        this.account = account;
        this.dateNow = LocalDateTime.now();
    }

    public void start(int withdraw) throws StopLoopException {
        String option;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

        System.out.println("\n\nSummary");
        System.out.println("Date : " + this.dateNow.format(formatter));
        System.out.println("Withdraw : $" + withdraw);
        System.out.println("Balance : $" + this.account.getBalance());

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
                throw new StopLoopException();
        }
    }
}
