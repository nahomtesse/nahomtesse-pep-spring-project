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

    @Autowired
    private MessageService messageService;

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

    @PostMapping("/messages")
    private ResponseEntity<Message> messaging(@RequestBody Message message) {
        Account realAccount = accountService.getAccountById(message.getPostedBy());

        if (realAccount != null) {
            Message messageCreated = messageService.messaging(message);
            if (messageCreated == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(messageCreated, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/messages/{messageId}")
    private ResponseEntity<?> updateMessageById(@PathVariable Integer messageId, @RequestBody Message newMessageText) {
        Message messagetoUpdate = messageService.getMessageById(messageId);

        if (messagetoUpdate == null) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        messagetoUpdate.setMessageText(newMessageText.getMessageText());

        Message updatedMessage = messageService.updateMessageById(messageId, messagetoUpdate);

        if (updatedMessage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(1, HttpStatus.OK);

    }

    @GetMapping("/messages")
    private ResponseEntity<List<Message>> allMessages() {
        List<Message> messages = messageService.allMessages();
        
        
        return ResponseEntity.ok(messages);
    }

}