package com.hjm.service.impl;

import com.hjm.dao.GoodDao;
import com.hjm.model.Good;
import com.hjm.service.GoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class GoodServiceImpl implements GoodService {
    @Resource
    private GoodDao goodDao;

    @Override
    public void insert(Good good) {
        good.setId(UUID.randomUUID().toString());
        goodDao.insert(good);
    }

    @Override
    public void delete(Good good) {
        goodDao.delete(good);
    }

    @Override
    public void deleteList(List<Good> list) {
        goodDao.deleteList(list);
    }

    @Override
    public void deleteAll(Good good) { goodDao.deleteAll(good); }

    @Override
    public void deleteBorrow(Good good) {
        goodDao.deleteBorrow(good);
    }

    @Override
    public void deleteAllBorrow(Good good) {
        goodDao.deleteAllBorrow(good);
    }

    @Override
    public Good getById(String id) { return goodDao.getById(id); }

    @Override
    public List<Good> getList(Good good) { return goodDao.getList(good); }

    @Override
    public List<Good> homePage() {
        return goodDao.homePage();
    }

    @Override
    public Good getOne(Good good) { return goodDao.getOne(good); }

    @Override
    public void update(Good good) { goodDao.update(good); }

    @Override
    public void updateState(Good good) {
        goodDao.updateState(good);
    }

    @Override
    public List<Good> searchByName(String goodName) {
        return goodDao.searchByName(goodName);
    }
}
