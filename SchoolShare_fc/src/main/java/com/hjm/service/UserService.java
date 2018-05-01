package com.hjm.service;

import com.hjm.model.User;

import java.util.List;

public interface UserService {
    void insert(User user);

    void delete(User user);
    void deleteList(List<User> list);

    User getById(String id);
    List<User> getList(User user);
    User getOne(User user);

    void update(User user);
}
