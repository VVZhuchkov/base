package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.ContactDao;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.dao.converter.ContactConverter;
import com.github.vvzhuchkov.base.dao.entity.ContactEntity;
import com.github.vvzhuchkov.base.model.Contact;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultContactDao implements ContactDao {
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
    public Contact saveNewContact(Contact contact) {
        ContactEntity contactEntity = ContactConverter.toEntity(contact);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(contactEntity);
        session.getTransaction().commit();
        session.close();
        return ContactConverter.fromEntity(contactEntity);
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

    @Override
    public List<Contact> getAllContacts() {
        final List<ContactEntity> contactEntityList = HibernateUtil.getSession().createQuery("from ContactEntity")
                .list();
        System.out.println(contactEntityList.size());
        return contactEntityList.stream()
                .map(ContactConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteContact(String login) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete ContactEntity ce where ce.login = :login")
                .setParameter("login", login)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
