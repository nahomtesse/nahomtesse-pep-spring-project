package com.example.service;

import java.util.List;

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

    public Message updateMessageById(int id, Message message) {
        if (message.getMessageText() == null || message.getMessageText() == "" || message.getMessageText().length() > 255) {
            return null;
        }

        return messageRepository.save(message);
    }

    public Message getMessageById(int id) {
        if (messageRepository.findById(id) == null) {
            return null;
        }

        return messageRepository.findById(id);
    }

    public List<Message> allMessages() {
        return messageRepository.findAll();
    }

    public void deleteMessageById(int id, Message message) {
        messageRepository.delete(message);
    }

    public List<Message> getAllMessageForUser(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }

}
