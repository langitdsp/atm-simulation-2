package com.mitrais.cdc.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(){

    }

    @Override
    public String toString() {
        return "Account Not Found";
    }
}
