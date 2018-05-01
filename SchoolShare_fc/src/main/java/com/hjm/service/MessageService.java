package com.hjm.service;

import com.hjm.model.Message;
import com.hjm.model.User;

import java.util.List;

public interface MessageService {
    void sendOne(Message message);
    void update(User receiver);
    List<Message> getAllMessage(User receiver);
}
