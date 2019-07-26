package com.mitrais.cdc.dao;

import com.mitrais.cdc.model.Journal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDaoImpl implements TransactionDao {
    private final static String CSV_FILE = "data/Journal";

    @Override
    public void createTrx(Journal transaction) {
        try {
            transaction.setDate(LocalDateTime.now());
            transaction.setId(UUID.randomUUID().toString());

            StringJoiner trx = new StringJoiner(",");
            trx.add(transaction.getId());
            trx.add(transaction.getDate().toString());
            trx.add(transaction.getType());
            trx.add(transaction.getSourceAccountNumber());
            trx.add(transaction.getDestinationAccountNumber());
            trx.add(String.valueOf(transaction.getAmount()));
            trx.add(transaction.getDescription());
            trx.add(String.valueOf(transaction.getBalance()));

            Files.write(Paths.get(CSV_FILE + transaction.getSourceAccountNumber() + ".csv"), trx.toString().getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Journal> getTransactionHistory(String accountNumber, int count) {
        try (Stream<String> lines = Files.lines(Paths.get(CSV_FILE))) {
            List<String> list = lines.collect(Collectors.toList());
            Collections.reverse(list);

            List<Journal> journalHistory = new ArrayList<>();

            int historySize = list.size();

            if(count > historySize){
                count = historySize;
            }

            for(int i = 0; i < count; i++){
                Journal journal = new Journal();
                String[] rawJournal = list.get(i).split(",");
                journal.setId(rawJournal[0]);
                journal.setDate(LocalDateTime.parse(rawJournal[1]));
                journal.setType(rawJournal[2]);
                journal.setDestinationAccountNumber(rawJournal[4]);
                journal.setAmount(Integer.parseInt(rawJournal[5]));
                journal.setDescription(rawJournal[6]);
                journal.setBalance(Integer.parseInt(rawJournal[7]));

                journalHistory.add(journal);
            }

            return journalHistory;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
