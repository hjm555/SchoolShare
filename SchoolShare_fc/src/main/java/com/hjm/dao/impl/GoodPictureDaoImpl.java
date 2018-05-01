package com.hjm.dao.impl;

import com.hjm.dao.GoodPictureDao;
import com.hjm.model.Good;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GoodPictureDaoImpl extends SqlSessionDaoSupport implements GoodPictureDao {
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory factory){
        super.setSqlSessionFactory(factory);
    }
    @Override
    public void delete(String url){ this.getSqlSession().delete("com.hjm.dao.GoodPictureDao.delete",url); }

    @Override
    public void deleteAll(Good good) {
        this.getSqlSession().delete("com.hjm.dao.GoodPictureDao.delete",good);
    }

    @Override
    public void insert(String url,Good good){
        Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        map.put("good", good);
        this.getSqlSession().insert("com.hjm.IGoodPictureDao.update",map);}
}
