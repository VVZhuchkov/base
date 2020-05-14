package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.AuthUserDao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultAuthUserDaoTest {

    @Test
    public void getInstance(){
        AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
        AuthUserDao authUserDao2= DefaultAuthUserDao.getInstance();
        assertEquals(authUserDao,authUserDao2);
    }

    @Test
    public void getByLogin(String login){
        AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
        authUserDao.getByLogin("vzhuchkov");
        String password = "5";
    }
}
