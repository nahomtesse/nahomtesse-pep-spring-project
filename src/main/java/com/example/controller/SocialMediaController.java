package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.*;
import com.example.service.*;


@RestController
public class SocialMediaController {
    
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    private ResponseEntity<Account> registration(@RequestBody Account account) {
        Account existingAccount = accountService.getAccountByUsername(account.getUsername());
        
        if(existingAccount == null) {
            Account created = accountService.registration(account);
            if (created == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(created, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    private ResponseEntity<Account> loggingIn(@RequestBody Account account) {
        Account loggedIn = accountService.logginIn(account);

        if (loggedIn == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else {
            return new ResponseEntity<>(loggedIn, HttpStatus.OK);
        }

    }



}