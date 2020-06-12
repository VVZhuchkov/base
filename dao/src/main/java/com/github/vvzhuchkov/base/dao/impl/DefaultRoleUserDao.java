package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.converter.ContactConverter;
import com.github.vvzhuchkov.base.dao.converter.RoleUserConverter;
import com.github.vvzhuchkov.base.dao.entity.ContactEntity;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.dao.RoleUserDao;
import com.github.vvzhuchkov.base.dao.entity.RoleUserEntity;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.model.RoleUser;
import org.hibernate.Session;
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
        RoleUserEntity roleUser = (RoleUserEntity) HibernateUtil.getSession().createQuery("from RoleUserEntity ru where ru.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        return roleUser.getRole();
    }

    @Override
    public Long getRatingUserByLogin(String login) {
        RoleUserEntity roleUser = (RoleUserEntity) HibernateUtil.getSession().createQuery("from RoleUserEntity ru where ru.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        return roleUser.getRating();
    }

    public void deleteRoleUser(String login) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from RoleUserEntity as rue where rue.login = :login")
                .setParameter("login", login)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public RoleUser saveNewRoleUser(RoleUser roleUser) {
        RoleUserEntity roleUserEntity = RoleUserConverter.toEntity(roleUser);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(roleUserEntity);
        session.getTransaction().commit();
        session.close();
        return RoleUserConverter.fromEntity(roleUserEntity);
    }
}