package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.RoleUserDao;
import com.github.vvzhuchkov.base.dao.entity.RoleUserEntity;
import com.github.vvzhuchkov.base.model.RoleUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;

public class DefaultRoleUserDao implements RoleUserDao {
    private static final Logger logOut = LoggerFactory.getLogger(DefaultRoleUserDao.class);
    public static volatile RoleUserDao instance;

    public static RoleUserDao getInstance() {
        RoleUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RoleUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRoleUserDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public String getRoleUserByLogin(String login) {
        RoleUserEntity roleUser;
        try {
            roleUser = (RoleUserEntity) HibernateUtil.getSession().createQuery("from RoleUserEntity ru where ru.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("Role not found by login {}", login);
            roleUser = null;
        }
        return roleUser.getRole();
    }

    @Override
    public Long getRatingUserByLogin(String login){
        RoleUserEntity roleUser;
        try {
            roleUser = (RoleUserEntity) HibernateUtil.getSession().createQuery("from RoleUserEntity ru where ru.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("Rating not found by login {}", login);
            roleUser = null;
        }
        return roleUser.getRating();
    }
}
