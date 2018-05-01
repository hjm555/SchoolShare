package com.hjm.dao.impl;

import com.hjm.dao.SharingRequestDao;
import com.hjm.model.SharingRequest;
import com.hjm.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SharingRequestDaoImpl extends SqlSessionDaoSupport implements SharingRequestDao {
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory factory){
        super.setSqlSessionFactory(factory);
    }
    @Override
    public void insert(SharingRequest sharingRequest) {
        this.getSqlSession().insert("com.hjm.dao.SharingRequest.insert",sharingRequest);
    }

    @Override
    public void delete(SharingRequest sharingRequest) {
        this.getSqlSession().delete("com.hjm.dao.SharingRequest.delete",sharingRequest);
    }

    @Override
    public void update(SharingRequest sharingRequest) {
        this.getSqlSession().update("com.hjm.dao.SharingRequest.update",sharingRequest);
    }

    @Override
    public List<SharingRequest> getOwnerList(User owner) {
        return this.getSqlSession().selectList("com.hjm.dao.SharingRequest.getOwnerList",owner);
    }

    @Override
    public List<SharingRequest> getRequesterList(User requester) {
        return this.getSqlSession().selectList("com.hjm.dao.SharingRequest.getRequesterList",requester);
    }
}
