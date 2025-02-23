package com.example.repository;

import com.example.entity.Account;
import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findByUsername(String username);
}
