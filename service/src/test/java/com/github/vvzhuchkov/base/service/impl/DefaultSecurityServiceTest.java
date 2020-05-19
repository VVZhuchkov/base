package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.service.SecurityService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultSecurityServiceTest {

    @Test
    public void getInstance(){
        SecurityService securityService = DefaultSecurityService.getInstance();
        SecurityService securityService2 = DefaultSecurityService.getInstance();
        assertEquals(securityService,securityService2);
    }
}
