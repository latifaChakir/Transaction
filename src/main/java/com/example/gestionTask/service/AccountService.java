package com.example.gestionTask.service;

import com.example.gestionTask.entity.Account;
import com.example.gestionTask.repository.AccountRepository;

import java.math.BigDecimal;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public void getAllAccounts() {
        accountRepository.findAll();
    }

    public Account updateAccount(Account account) {
        return accountRepository.update(account);
    }

    public void deleteAccount(int accountId) {
        accountRepository.delete(accountId);
    }

    public void transferMoney(long fromAccountId, long toAccountId, BigDecimal amount) throws Exception {
        Account fromAccount = accountRepository.findById(fromAccountId);
        Account toAccount = accountRepository.findById(toAccountId);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds for transfer");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
