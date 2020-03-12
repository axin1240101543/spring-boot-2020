package com.darren.center.springboot.table;

import com.darren.center.springboot.BaseTest;
import com.darren.center.springboot.dao.read.UserReaderDao;
import com.darren.center.springboot.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public class UserTest extends BaseTest{

    @Autowired
    private ApplicationContext context;

    @Test
    public void get(){
        UserReaderDao userReaderDao = context.getBean(UserReaderDao.class);
        User user = userReaderDao.selectByPrimaryKey((long)4);
        System.out.println(user);
    }

}
