package com.hjm.test;

import com.hjm.model.Good;
import com.hjm.model.User;
import com.hjm.service.GoodService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class GoodServiceTest extends BaseTest {
    @Resource
    private GoodService goodService;

    Logger logger = Logger.getLogger("com.hjm.test.UserServiceTest");

    @Test
    @Transactional
    @Rollback
    public void testinsert(){
        Good good=new Good();
        User user=new User();
        user.setName("15016241165");
    }
}
