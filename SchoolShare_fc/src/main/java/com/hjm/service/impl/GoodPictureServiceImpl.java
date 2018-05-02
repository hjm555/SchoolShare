package com.hjm.service.impl;

import com.hjm.dao.GoodPictureDao;
import com.hjm.model.Good;
import com.hjm.service.GoodPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodPictureServiceImpl implements GoodPictureService {
    @Resource
    private GoodPictureDao goodPictureDao;

    @Override
    public void delete(String url) { goodPictureDao.delete(url);}

    @Override
    public void deleteAll(Good good){ goodPictureDao.deleteAll(good);}

    @Override
    public void insert(String url,Good good) { goodPictureDao.insert(url,good);}

    @Override
    public void insertList(Good good) {
        goodPictureDao.insertList(good);
    }
}
