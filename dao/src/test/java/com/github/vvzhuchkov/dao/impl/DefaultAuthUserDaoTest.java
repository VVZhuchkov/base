package com.github.vvzhuchkov.dao.impl;


import com.github.vvzhuchkov.dao.AuthUserDao;
import com.github.vvzhuchkov.model.AuthUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultAuthUserDaoTest {

    @Test
   public void getEmployees(){
        final int amountOfEmployees  = 4;
        DefaultAuthUserDao authUserDao = new DefaultAuthUserDao();
        assertEquals(amountOfEmployees, authUserDao.getEmployees().size());
    }

    @Test
    public void getInstance(){
        AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
        AuthUserDao authUserDao2= DefaultAuthUserDao.getInstance();
        assertEquals(authUserDao,authUserDao2);
    }
}
