package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.RoleUserDao;
import com.github.vvzhuchkov.base.model.RoleUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DefaultRoleUserDaoTest {
    RoleUserDao roleUserDao = DefaultRoleUserDao.getInstance();

    @Test
    void getInstance() {
        RoleUserDao roleUserDao = DefaultRoleUserDao.getInstance();
        RoleUserDao roleUserDao2 = DefaultRoleUserDao.getInstance();
        assertEquals(roleUserDao, roleUserDao2);
    }

    @Test
    void getRoleUserByLogin() {
        RoleUser roleUser = roleUserDao.saveNewRoleUser(new RoleUser("login", "user", 0L));
        String role = roleUserDao.getRoleUserByLogin(roleUser.getLogin());
        assertEquals(role, "user");
        roleUserDao.deleteRoleUser(roleUser.getLogin());
    }

    @Test
    void getRatingUserByLogin() {
        RoleUser roleUser = roleUserDao.saveNewRoleUser(new RoleUser("login", "user", 0L));
        Long rating = roleUserDao.getRatingUserByLogin(roleUser.getLogin());
        assertEquals(rating, (Long) 0L);
        roleUserDao.deleteRoleUser(roleUser.getLogin());
    }
}
