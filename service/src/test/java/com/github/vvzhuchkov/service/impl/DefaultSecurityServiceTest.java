package com.github.vvzhuchkov.service.impl;

import com.github.vvzhuchkov.model.AuthUser;
import com.github.vvzhuchkov.service.SecurityService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultSecurityServiceTest {

    @Test
    public void login (){
        int id = 5200005;
        String password = "5";
     SecurityService securityService = DefaultSecurityService.getInstance();
        AuthUser user = securityService.getEmployees().get(id);
        assertEquals(password, user.getPassword());
    }

    @Test
    public static void getInstance(){
        SecurityService securityService = DefaultSecurityService.getInstance();
        SecurityService securityService2 = DefaultSecurityService.getInstance();
        assertEquals(securityService,securityService2);
    }
}
