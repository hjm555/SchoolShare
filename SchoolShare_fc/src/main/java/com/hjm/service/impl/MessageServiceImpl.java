package com.hjm.service.impl;

import com.hjm.dao.MessageDao;
import com.hjm.model.Message;
import com.hjm.model.User;
import com.hjm.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;
    @Override
    public void sendOne(Message message) {
        message.setId(UUID.randomUUID().toString());
        messageDao.sendOne(message);
    }

    @Override
    public void update(User receiver) {
        messageDao.update(receiver);
    }

    @Override
    public List<Message> getAllMessage(User receiver) {
        return messageDao.getAllMessage(receiver);
    }
}
