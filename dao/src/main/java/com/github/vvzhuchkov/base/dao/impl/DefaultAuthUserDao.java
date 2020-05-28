package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.*;
import com.github.vvzhuchkov.base.dao.converter.AuthUserConverter;
import com.github.vvzhuchkov.base.dao.converter.RoleUserConverter;
import com.github.vvzhuchkov.base.dao.entity.AuthUserEntity;
import com.github.vvzhuchkov.base.dao.entity.RoleUserEntity;
import com.github.vvzhuchkov.base.model.*;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger logOut = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    public static volatile AuthUserDao instance;

    public static AuthUserDao getInstance() {
        AuthUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultAuthUserDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthUser getByLogin(String login) {
        AuthUserEntity authUser;
        try {
            authUser = (AuthUserEntity) HibernateUtil.getSession().createQuery("from AuthUserEntity au where au.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("User not found by login {}", login);
            authUser = null;
        }
        return AuthUserConverter.fromEntity(authUser);
    }

    @Override
    public List<String> getAllAuthEmails() {
        final List<String> result = HibernateUtil.getSession().createQuery("select au.email from AuthUserEntity au")
                .list();
        return result;
    }

    @Override
    public void saveNewRegUser(AuthUser user) {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(user);
        setNewUserRole(user.getLogin());
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(authUserEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void setNewUserRole(String login) {
        RoleUser roleUser = new RoleUser(login, "user", 0L);
        RoleUserEntity roleUserEntity = RoleUserConverter.toEntity(roleUser);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(roleUserEntity);
        session.getTransaction().commit();
        session.close();
    }
}