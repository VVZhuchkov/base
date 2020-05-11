package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.RoleUserDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultRoleUserDao;
import com.github.vvzhuchkov.base.service.RoleUserService;

public class DefaultRoleUserService implements RoleUserService {
    private RoleUserDao roleUserDao = DefaultRoleUserDao.getInstance();

    private static volatile RoleUserService instance;

    public static RoleUserService getInstance() {
        RoleUserService localInstance = instance;
        if (localInstance == null) {
            synchronized (RoleUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRoleUserService();
                }
            }
        }
        return localInstance;
    }

    public String getRoleUserByLogin(String login){
        return roleUserDao.getRoleUserByLogin(login);
    }

    public Long getRatingUserByLogin(String login){ return  roleUserDao.getRatingUserByLogin(login); }
}
