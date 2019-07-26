package com.mitrais.cdc.exception;

public class NotEnoughAmountException extends Exception {

    public NotEnoughAmountException(){

    }

    @Override
    public String toString() {
        return "Not enough amount";
    }
}
