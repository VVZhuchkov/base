package com.github.vvzhuchkov.service.impl;

import com.github.vvzhuchkov.dao.AuthUserDao;
import com.github.vvzhuchkov.model.AuthUser;
import com.github.vvzhuchkov.service.SecurityService;
import com.github.vvzhuchkov.dao.impl.DefaultAuthUserDao;

import java.util.List;
import java.util.Map;

public class DefaultSecurityService implements SecurityService {
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    private static volatile SecurityService instance;

    public static SecurityService getInstance() {
        SecurityService localInstance = instance;
        if (localInstance == null) {
            synchronized (SecurityService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultSecurityService();
                }
            }
        }
        return localInstance;
    }

    public AuthUser login(int id, String password) {
        AuthUser user = authUserDao.getEmployees().get(id);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public Map<Integer, AuthUser> getEmployees() {
        return authUserDao.getEmployees();
    }
}
