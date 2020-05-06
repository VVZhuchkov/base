package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.AuthUserDao;
import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.service.SecurityService;
import com.github.vvzhuchkov.base.dao.impl.DefaultAuthUserDao;

import java.util.List;

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

    public AuthUser login(String login, String password) {
        AuthUser user = authUserDao.getByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public AuthUser registration(String login, String password, String email) {
        List<String> allAuthEmails = DefaultAuthUserDao.getInstance().getAllAuthEmails();
        AuthUser user = authUserDao.getByLogin(login);
        if ((user == null) && (allAuthEmails.contains(email))) {
            return new AuthUser(login, password, null);
        }
        if ((user == null) && !(allAuthEmails.contains(email))) {
            return new AuthUser(login, password, email);
        }
            return null;
        }

    public void saveNewRegUser (AuthUser user){
        authUserDao.saveNewRegUser(user);
    }
}