package com.hjm.dao.impl;

import com.hjm.dao.MessageDao;
import com.hjm.model.Message;
import com.hjm.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class MessageDaoImpl extends SqlSessionDaoSupport implements MessageDao {
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory factory){
        super.setSqlSessionFactory(factory);
    }

    @Override
    public void sendOne(Message message) {
        this.getSqlSession().insert("com.hjm.dao.MessageDao.sendOne",message);
    }

    @Override
    public void update(User receiver) {
        this.getSqlSession().update("com.hjm.dao.MessageDao.update",receiver);
    }

    @Override
    public List<Message> getAllMessage(User user) {
        return this.getSqlSession().selectList("com.hjm.dao.MessageDao.getAllMessage",user);
    }
}
