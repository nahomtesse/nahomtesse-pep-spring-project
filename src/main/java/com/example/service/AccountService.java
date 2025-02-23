package com.example.service;


import java.util.Optional;

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
       
       if (account.getUsername() == null || account.getUsername() == "" || passLength < 4 ) {
            return null;
        }

        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent()) {
            return null;
        }

        return accountRepository.save(account);

    }
}
