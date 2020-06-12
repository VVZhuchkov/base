package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.AuthUserDao;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.service.SecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultSecurityServiceTest {

    @Test
    void getInstance() {
        SecurityService securityService = DefaultSecurityService.getInstance();
        SecurityService securityService2 = DefaultSecurityService.getInstance();
        assertEquals(securityService, securityService2);
    }

    @Mock
    AuthUserDao authUserDao;

    @InjectMocks
    DefaultSecurityService securityService;

    @Test
    void testLoginNotExist() {
        when(authUserDao.getByLogin("login")).thenReturn(null);
        AuthUser authUser = securityService.login("login", "password");
        assertNull(authUser);
    }

    @Test
    void testLoginCorrect() {
        when(authUserDao.getByLogin("login")).thenReturn(new AuthUser("login", "password", "email"));
        AuthUser authUserFromDb = securityService.login("login", "password");
        assertNotNull(authUserFromDb.getLogin(), "login");
        assertEquals(authUserFromDb.getLogin(), "login");
        assertEquals(authUserFromDb.getPassword(), "password");
        assertEquals(authUserFromDb.getEmail(), "email");
    }

    @Test
    void testLoginWrongPass() {
        when(authUserDao.getByLogin("login")).thenReturn(new AuthUser("login", "password", "email"));
        AuthUser authUserFromDb = securityService.login("login", "password2");
        assertNull(authUserFromDb);
    }

    @Test
    void saveUserTest() {
        when(authUserDao.getByLogin("login")).thenReturn(new AuthUser("login", "password", "email"));
        final AuthUser authUserFromDb = securityService.login("login", "password");
        assertNotNull(authUserFromDb);
        when(authUserDao.saveNewRegUser(authUserFromDb)).thenReturn(authUserFromDb);
        final AuthUser authUser = securityService.saveNewRegUser(authUserFromDb);
        assertNotNull(authUser);
        assertEquals(authUser.getLogin(), authUserFromDb.getLogin());
        assertEquals(authUser.getPassword(), authUserFromDb.getPassword());
        assertEquals(authUser.getEmail(), authUserFromDb.getEmail());
    }

    @Test
    void correctRegistrationUserTest() {
        when(authUserDao.getByLogin("login")).thenReturn(new AuthUser("login", "password", "email"));
        final AuthUser authUserFromDb = securityService.registration("login", "password", "email");
        assertNull(authUserFromDb);
    }

    @Test
    void incorrectRegistrationUserTest() {
        when(authUserDao.getByLogin("login")).thenReturn(null);
        final AuthUser authUserFromDb = securityService.registration("login", "password", "email");
        assertNotNull(authUserFromDb);
    }

    @Test
    void incorrectEmailUserTest() {
        String email = "mail@mail.com";
        when(authUserDao.getByLogin("login")).thenReturn(null);
        List<String> existedEmails = Collections.singletonList(email);
        when(authUserDao.getAllAuthEmails()).thenReturn(existedEmails);
        final AuthUser authUserFromDb = securityService.registration("login", "password", email);
        assertNull(authUserFromDb.getEmail());
    }
}
