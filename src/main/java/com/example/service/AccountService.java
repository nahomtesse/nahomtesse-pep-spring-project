package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account registration(Account account) {
       String pass = account.getPassword();
       int passLength = pass.length();
       
        if (account.getUsername() == " " || account.getUsername() == "" || passLength < 4 ) {
            return null;
        }

        return accountRepository.save(account);

    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public Account logginIn(Account loginAccount) {
        Account account = accountRepository.findByUsername(loginAccount.getUsername());

        if (account != null && account.getPassword().equals(loginAccount.getPassword())) {
            return accountRepository.save(account);
        }
        return null;
    }
}
