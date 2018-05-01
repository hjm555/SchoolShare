package com.hjm.dao;

import com.hjm.model.Good;

import java.util.List;

public interface GoodDao extends IBaseDao<Good> {
    List<Good> searchByName(String goodName);
    List<Good> homePage();
    void deleteAll(Good good);
    void deleteBorrow(Good good);
    void deleteAllBorrow(Good good);
    void updateState(Good good);
}
