package com.hjm.dao;

import com.hjm.model.Good;

import java.util.List;

public interface GoodDao extends IBaseDao<Good> {
    List<Good> searchByName(String goodName);  //从物品表中找出物品名或描述与goodName相似的所有记录
    List<Good> homePage();                     //列出物品表中的所有记录，并按物品发布时间降序排序
    void deleteAll(Good good);                 //
    void deleteBorrow(Good good);
    void deleteAllBorrow(Good good);
    void updateState(Good good);
}
