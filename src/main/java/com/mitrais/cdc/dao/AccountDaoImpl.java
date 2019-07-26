package com.mitrais.cdc.dao;

import com.mitrais.cdc.model.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class AccountDaoImpl implements AccountDao {

    private final static String CSV_FILE = "data/AccountList.csv";

    @Override
    public Account getUserByAccountNumber(String accountNumber) {
        try(Stream<String> lines = Files.lines(Paths.get(CSV_FILE))){
            Optional<String> result = lines
                    .filter(account -> account.split(",")[1].equals(accountNumber))
                    .findFirst();

            String[] userAccount = result.get().split(",");

            Account account = new Account(
                    userAccount[0],
                    userAccount[1],
                    userAccount[2],
                    Integer.parseInt(userAccount[3])
            );

            return account;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account loginUser(String accountNumber, String pin) {
        try(Stream<String> lines = Files.lines(Paths.get(CSV_FILE));){
            
            Optional<String> result = lines
                    .filter(account -> account.split(",")[1].equals(accountNumber) && account.split(",")[2].equals(pin))
                    .findFirst();

            String[] userAccount = result.get().split(",");

            Account account = new Account(
                    userAccount[0],
                    userAccount[1],
                    userAccount[2],
                    Integer.parseInt(userAccount[3])
            );

            return account;

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
