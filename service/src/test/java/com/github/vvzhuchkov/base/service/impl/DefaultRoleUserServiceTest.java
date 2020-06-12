package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.RoleUserDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultCarDao;
import com.github.vvzhuchkov.base.model.RoleUser;
import com.github.vvzhuchkov.base.service.RoleUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultRoleUserServiceTest {

    @Test
    void getInstance() {
        RoleUserService roleUserService = DefaultRoleUserService.getInstance();
        RoleUserService roleUserService2 = DefaultRoleUserService.getInstance();
        assertEquals(roleUserService, roleUserService2);
    }

    @Mock
    RoleUserDao roleUserDao;

    @InjectMocks
    DefaultRoleUserService roleUserService;

    @Test
    void getRoleUserByLoginTest(){
        when(roleUserDao.getRoleUserByLogin("login")).thenReturn(new RoleUser("login","role", 0L).getRole());
        final String existedRole = roleUserService.getRoleUserByLogin("login");
        assertEquals(existedRole, "role");
    }

    @Test
    void getRatingUserByLoginTest(){
        when(roleUserDao.getRatingUserByLogin("login")).thenReturn(new RoleUser("login","role", 1L).getRating());
        final Long existedRating = roleUserService.getRatingUserByLogin("login");
        assertEquals(existedRating, (Long)1L);
    }

}
