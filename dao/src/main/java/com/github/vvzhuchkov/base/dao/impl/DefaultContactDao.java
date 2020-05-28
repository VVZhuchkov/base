package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.ContactDao;
import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.converter.ContactConverter;
import com.github.vvzhuchkov.base.dao.entity.ContactEntity;
import com.github.vvzhuchkov.base.model.Contact;
import org.hibernate.Session;

import javax.persistence.NoResultException;

public class DefaultContactDao implements ContactDao{
    public static volatile ContactDao instance;

    public static ContactDao getInstance() {
        ContactDao localInstance = instance;
        if (localInstance == null) {
            synchronized (ContactDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultContactDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveNewContact(Contact contact) {
        ContactEntity contactEntity = ContactConverter.toEntity(contact);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(contactEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Contact getContactByLogin(String login) {
        ContactEntity contact;
        try {
            contact = (ContactEntity) HibernateUtil.getSession().createQuery("from ContactEntity ce where ce.login = :login")
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            contact = null;
        }
        return ContactConverter.fromEntity(contact);
    }
}
