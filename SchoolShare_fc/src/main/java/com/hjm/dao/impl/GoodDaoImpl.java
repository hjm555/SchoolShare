package com.hjm.dao.impl;

import com.hjm.dao.GoodDao;
import com.hjm.model.Good;
import com.hjm.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodDaoImpl extends BaseDaoImpl<Good> implements GoodDao {
    public GoodDaoImpl(){
        setNs("Good");
    }

    @Override
    public List<Good> searchByName(String goodName) {
        return this.getSqlSession().selectList("com.hjm.dao.GoodDao.searchByName", goodName);
    }

    @Override
    public List<Good> homePage() {
        return this.getSqlSession().selectList("com.hjm.dao.GoodDao.homepage");
    }

    @Override
    public void deleteAll(Good good) { this.getSqlSession().delete("com.hjm.dao.GoodDao.deleteAll",good);
    }

    @Override
    public void deleteBorrow(Good good) {
        this.getSqlSession().update("com.hjm.dao.GoodDao.deleteBorrow",good);
    }

    @Override
    public void deleteAllBorrow(Good good) {
        this.getSqlSession().update("com.hjm.dao.GoodDao.deleteBorrow",good);
    }

    @Override
    public void updateState(Good good) {
        this.getSqlSession().update("com.hjm.dao.GoodDao.updateState",good);
    }
}
