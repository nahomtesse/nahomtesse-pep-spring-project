package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message messaging(Message message) {

        String mess = message.getMessageText();
        int messLength = mess.length();

        if (mess == null || mess == "" || messLength > 255) {
            return null;
        }

        return messageRepository.save(message);
    }

}
