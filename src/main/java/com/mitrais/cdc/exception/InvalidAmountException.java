package com.mitrais.cdc.exception;

public class InvalidAmountException extends Exception {

    public InvalidAmountException(){

    }

    @Override
    public String toString() {
        return "Invalid Amount";
    }
}
