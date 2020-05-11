package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.service.SecurityService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultSecurityServiceTest {

    @Test
    public void getInstance(){
        SecurityService securityService = DefaultSecurityService.getInstance();
        SecurityService securityService2 = DefaultSecurityService.getInstance();
        assertEquals(securityService,securityService2);
    }
}
