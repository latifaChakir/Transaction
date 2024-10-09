package com.example.gestionTask;

import com.example.gestionTask.entity.Account;
import com.example.gestionTask.service.AccountService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        Account fromAccount = new Account(1L, "John Doe", new BigDecimal("100.00"));
        Account toAccount = new Account(2L, "Jane Doe", new BigDecimal("500.00"));

        accountService.addAccount(fromAccount);
        accountService.addAccount(toAccount);

        System.out.println("Solde initial du compte de John: " + fromAccount.getBalance());
        System.out.println("Solde initial du compte de Jane: " + toAccount.getBalance());

        BigDecimal transferAmount = new BigDecimal("200.00");

        try {
            accountService.transferMoney(1L, 2L, transferAmount);

            Account updatedFromAccount = accountService.getAccountById(1L);
            Account updatedToAccount = accountService.getAccountById(2L);

            System.out.println("Solde après transfert du compte de John: " + updatedFromAccount.getBalance());
            System.out.println("Solde après transfert du compte de Jane: " + updatedToAccount.getBalance());
        } catch (Exception e) {
            System.out.println("Erreur lors du transfert : " + e.getMessage());
        }
    }
}
