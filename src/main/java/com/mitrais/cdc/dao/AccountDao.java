package com.mitrais.cdc.dao;

import com.mitrais.cdc.model.Account;

public interface AccountDao {
    public Account getUserByAccountNumber(String accountNumber);
    public Account loginUser(String accountNumber, String pin);
}
