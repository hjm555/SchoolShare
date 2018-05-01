package com.hjm.dao;

import com.hjm.model.Message;
import com.hjm.model.User;

import java.util.List;

public interface MessageDao {
    void sendOne(Message message);
    void update(User receiver);
    List<Message> getAllMessage(User user);
}
