package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.ContactDao;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.model.Contact;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//100%
class DefaultContactDaoTest {
    ContactDao contactDao = DefaultContactDao.getInstance();

    @Test
    void getInstance() {
        ContactDao contactDao = DefaultContactDao.getInstance();
        ContactDao contactDao2 = DefaultContactDao.getInstance();
        assertEquals(contactDao, contactDao2);
    }

    @Test
    void saveNewContact() {
        final Contact contact = new Contact("vzhuchkov", "Zhuchkov", "Vladimir", "54321", null);
        final Contact savedContact = contactDao.saveNewContact(contact);
        assertEquals(contact.getLogin(), savedContact.getLogin());
        contactDao.deleteContact(contact.getLogin());
    }

    @Test
    void getContactByLogin() {
        final Contact contact = contactDao.saveNewContact(new Contact("vzhuchkov", "Zhuchkov", "Vladimir", "54321"));
        Contact savedContact = contactDao.getContactByLogin(contact.getLogin());
        assertEquals(contact.getLogin(), savedContact.getLogin());
        contactDao.deleteContact(contact.getLogin());
    }

  @Test
    void getAllContacts() {
        final Contact contact = new Contact("vzhuchkov", "Zhuchkov", "Vladimir", "54321");
        final Contact contact2 = new Contact("ibizun", "Bizun", "Igor", "98765");
        final Contact savedContact = contactDao.saveNewContact(contact);
        final Contact savedContact2 = contactDao.saveNewContact(contact2);
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact2);
        contactList.add(contact);
        List<Contact> savedList = contactDao.getAllContacts();
        assertEquals(savedList.size(), contactList.size());
        for (int i = 0; i < savedList.size(); i++) {
            assertEquals(savedList.get(i).getLogin(), contactList.get(i).getLogin());
        }
        contactDao.deleteContact(savedContact.getLogin());
        contactDao.deleteContact(savedContact2.getLogin());
    }

    @Test
    void deleteContact(){
        final Contact contact = new Contact("vzhuchkov", "Zhuchkov", "Vladimir", "54321");
        final Contact savedContact = contactDao.saveNewContact(contact);
        String contactLogin = savedContact.getLogin();
        assertNotNull(contactDao.getContactByLogin(contactLogin));
        contactDao.deleteContact(contactLogin);
        assertNull(contactDao.getContactByLogin(contactLogin));
    }

    @AfterClass
    public void cleanUp() {
        HibernateUtil.closeEMFactory();
    }
}
