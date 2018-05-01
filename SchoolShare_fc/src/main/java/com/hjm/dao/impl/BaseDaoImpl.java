package com.hjm.dao.impl;

import com.hjm.dao.IBaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.util.List;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements IBaseDao<T> {
    private String ns;
    public void setNs(String ns){
        this.ns = ns;
    }

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory factory){
        super.setSqlSessionFactory(factory);
    }

    @Override
    public T getById(String id) {
        return this.getSqlSession().selectOne("com.hjm.dao."+ns+"Dao.getById", id);
    }

    @Override
    public T getOne(T t) {
        return this.getSqlSession().selectOne("com.hjm.dao."+ns+"Dao.getOne", t);
    }

    @Override
    public List<T> getList(T t) {
        return this.getSqlSession().selectList("com.hjm.dao."+ns+"Dao.getList", t);
    }
    @Override
    public void insert(T t) {
        this.getSqlSession().insert("com.hjm.dao."+ns+"Dao.insert", t);
    }

    @Override
    public void update(T t) {
        this.getSqlSession().update("com.hjm.dao."+ns+"Dao.update", t);
    }

    @Override
    public void delete(T t) {
        this.getSqlSession().delete("com.hjm.dao."+ns+"Dao.delete",t);
    }

    @Override
    public void deleteList(List<T> list) {
        this.getSqlSession().delete("com.hjm.dao."+ns+"Dao.deleteList", list);
    }
}
