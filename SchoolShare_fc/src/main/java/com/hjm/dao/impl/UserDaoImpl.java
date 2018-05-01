package com.hjm.dao.impl;

import com.hjm.dao.UserDao;
import com.hjm.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    public UserDaoImpl(){
        setNs("User");
    }
}
