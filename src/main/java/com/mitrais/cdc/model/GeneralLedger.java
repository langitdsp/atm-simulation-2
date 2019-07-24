package com.mitrais.cdc.model;

import java.time.LocalDateTime;

public class GeneralLedger {

    private String id;
    private LocalDateTime date;
    private String destinationAccountNumber;
    private String sourceAccountNumber;
    private int amount;
    private String description;
}
