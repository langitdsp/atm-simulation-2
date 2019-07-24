package com.mitrais.cdc.exception;

public class StopLoopException extends Exception {

    public StopLoopException(){

    }

    @Override
    public String toString() {
        return "Loop stopped";
    }
}
