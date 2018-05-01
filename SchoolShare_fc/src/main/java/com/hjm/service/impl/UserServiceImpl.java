package com.hjm.service.impl;

import com.hjm.dao.UserDao;
import com.hjm.model.User;
import com.hjm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public void insert(User user) {
        user.setId(UUID.randomUUID().toString());
        userDao.insert(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void deleteList(List<User> user) {
        userDao.deleteList(user);
    }

    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getList(User user) {
        return userDao.getList(user);
    }

    @Override
    public User getOne(User user) {
        return userDao.getOne(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
