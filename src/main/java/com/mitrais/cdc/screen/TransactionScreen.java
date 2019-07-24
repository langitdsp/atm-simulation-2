package com.mitrais.cdc.screen;

import java.util.Scanner;

import com.mitrais.cdc.exception.StopLoopException;
import com.mitrais.cdc.model.Account;

public class TransactionScreen {
    private Account account;
    private Scanner scanner;

    public TransactionScreen(Account account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
    }

    public void start() {

        Boolean isContinue = true;

        while(isContinue){
            String option = "";

            System.out.println("\n\nTransaction Screen");
            System.out.println("1. Withdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Exit");
            System.out.println("Please choose option[3]: ");

            option = this.scanner.nextLine();

            switch (option) {
                case "1":
                    WithdrawScreen withdrawScreen = new WithdrawScreen(this.account, this.scanner);
                    try{
                        withdrawScreen.start();
                    }catch (StopLoopException e){
                        isContinue = false;
                        break;
                    }
                    break;
                case "2":
                    FundTransferScreen fundTransferScreen = new FundTransferScreen(this.account, this.scanner);
                    try{
                        fundTransferScreen.start();
                    }catch (StopLoopException e){
                        isContinue = false;
                        break;
                    }
                    break;
                case "3":
                    isContinue = false;
                    break;
                default:
                    isContinue = false;
                    break;
            }
        }

    }
}