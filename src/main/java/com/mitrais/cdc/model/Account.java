package com.mitrais.cdc.model;

public class Account {
    private String name;
    private String pin;
    private int balance;
    private String accountNumber;

    public Account(String name, String accountNumber, String pin, int balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public Account() {

    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNUmber) {
        this.accountNumber = accountNumber;
    }

}