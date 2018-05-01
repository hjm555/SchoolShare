package com.hjm.service;

import com.hjm.model.Good;

import java.util.List;

public interface GoodService {
    void insert(Good good);
    void delete(Good good);
    void deleteList(List<Good> good);
    void deleteAll(Good good);
    void deleteBorrow(Good good);
    void deleteAllBorrow(Good good);
    Good getById(String id);
    List<Good> getList(Good good);
    List<Good> homePage();
    Good getOne(Good good);
    void update(Good good);
    void updateState(Good good);
    List<Good> searchByName(String goodName);
}
