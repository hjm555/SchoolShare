package com.hjm.test;

import com.hjm.model.User;
import com.hjm.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest extends BaseTest {
    @Resource
    UserService userService;

    Logger logger = Logger.getLogger("com.hjm.test.UserServiceTest");

    @Test
    @Transactional
    @Rollback
    public void testInsert(){
        User user = new User();
        user.setName("test");
        user.setPassword("test");
        user.setPhoneNumber("15626433255");
        user.setSex("F");
        user.setUrl("www.baidu.com");
        userService.insert(user);
        logger.info(user);
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete(){
        User user = new User();
        user.setId("test");
        userService.delete(user);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteList(){
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId("test");
        users.add(user);
        userService.deleteList(users);
    }

    @Test
    @Transactional
    @Rollback
    public void testGetById(){
        User user = userService.getById("00000000-00000000-0000-0000-00000000");
        logger.info(user);
    }

    @Test
    @Transactional
    @Rollback
    public void testGetList(){
        List<User> userList = userService.getList(new User());
        for (User user: userList){
            logger.info(user);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void testgetOne(){
        User user = new User();
        user.setName("root");
        user = userService.getOne(user);
        if (user != null){
            logger.info(user);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void testupdate(){
        User user=new User();
        user.setPassword("123456");
        userService.update(user);
        userService.getOne(user);
        logger.info(user);
    }
}
