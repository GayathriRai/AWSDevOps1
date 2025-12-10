package com.example.bankingapp.service;

import com.example.bankingapp.model.Account;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    private final Map<Long, Account> accounts = new HashMap<>();
    private long nextId = 1;

    public Account createAccount(String name, double initialBalance) {
        Account acc = new Account(name, initialBalance);
        acc.setId(nextId++);
        accounts.put(acc.getId(), acc);
        return acc;
    }

    public Account getAccount(Long id) {
        return accounts.get(id);
    }

    public String deposit(Long id, double amount) {
        Account acc = accounts.get(id);
        if (acc == null) return "Account not found";
        acc.setBalance(acc.getBalance() + amount);
        return "Amount Deposited!";
    }

    public String withdraw(Long id, double amount) {
        Account acc = accounts.get(id);
        if (acc == null) return "Account not found";
        if (acc.getBalance() < amount) return "Insufficient Balance!";
        acc.setBalance(acc.getBalance() - amount);
        return "Amount Withdrawn!";
    }
}
