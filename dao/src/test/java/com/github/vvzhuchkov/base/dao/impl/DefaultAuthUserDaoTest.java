package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.AuthUserDao;
import com.github.vvzhuchkov.base.dao.RoleUserDao;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.RoleUser;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//100%
class DefaultAuthUserDaoTest {
    AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    RoleUserDao roleUserDao = DefaultRoleUserDao.getInstance();

    @Test
    void getInstance() {
        AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
        AuthUserDao authUserDao2 = DefaultAuthUserDao.getInstance();
        assertEquals(authUserDao, authUserDao2);
    }

    @Test
    void getByLogin() {
        final AuthUser authUser = new AuthUser("login", "password", "email");
        final AuthUser savedUser = authUserDao.saveNewRegUser(authUser);
        final AuthUser authUserFromBase = authUserDao.getByLogin(authUser.getLogin());
        assertNotNull(savedUser);
        assertEquals(authUserFromBase.getLogin(), authUser.getLogin());
        assertEquals(authUserFromBase.getPassword(), authUser.getPassword());
        assertEquals(authUserFromBase.getEmail(), authUser.getEmail());
        authUserDao.deleteUser(authUser.getLogin());
        roleUserDao.deleteRoleUser(authUser.getLogin());
    }


    @Test
    void getAllAuthEmails() {
        final AuthUser authUser = new AuthUser("login", "password", "email");
        final AuthUser authUser2 = new AuthUser("login2", "password2", "email2");
        final AuthUser savedUser = authUserDao.saveNewRegUser(authUser);
        final AuthUser savedUser2 = authUserDao.saveNewRegUser(authUser2);
        final List<String> savedEmails = authUserDao.getAllAuthEmails();
        List<String> testingEmails = new ArrayList<>();
        testingEmails.add(authUser.getEmail());
        testingEmails.add(authUser2.getEmail());
        assertEquals(savedEmails, testingEmails);
        authUserDao.deleteUser(authUser.getLogin());
        roleUserDao.deleteRoleUser(authUser.getLogin());
        authUserDao.deleteUser(authUser2.getLogin());
        roleUserDao.deleteRoleUser(authUser2.getLogin());
    }


    @Test
    void saveNewRegUser() {
        final AuthUser authUser = new AuthUser("login", "password", "email");
        final AuthUser savedUser = authUserDao.saveNewRegUser(authUser);
        assertEquals(authUser.getLogin(), savedUser.getLogin());
        assertEquals(authUser.getPassword(), savedUser.getPassword());
        assertEquals(authUser.getEmail(), savedUser.getEmail());
        authUserDao.deleteUser(authUser.getLogin());
        roleUserDao.deleteRoleUser(authUser.getLogin());
    }

    @Test
    void setNewUserRole() {
        final RoleUser roleUser = new RoleUser("login", "null", 0L);
        final RoleUser savedRoleUser = authUserDao.setNewUserRole(roleUser.getLogin());
        assertNotEquals(roleUser.getRole(), savedRoleUser.getRole());
        roleUserDao.deleteRoleUser(roleUser.getLogin());
    }

    @Test
    void deleteUser() {
        final AuthUser authUser = new AuthUser("login", "password", "email");
        final AuthUser savedUser = authUserDao.saveNewRegUser(authUser);
        assertNotNull(authUserDao.getByLogin("login"));
        authUserDao.deleteUser(savedUser.getLogin());
        roleUserDao.deleteRoleUser(authUser.getLogin());
        assertNull(authUserDao.getByLogin(savedUser.getLogin()));
    }

    @AfterClass
    public void cleanUp() {
        HibernateUtil.closeEMFactory();
    }
}
