package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.ContactDao;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.service.ContactService;
import com.github.vvzhuchkov.base.service.RestitutionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultContactServiceTest {

    @Test
    void getInstance() {
        ContactService contactService = DefaultContactService.getInstance();
        ContactService contactService2 = DefaultContactService.getInstance();
        assertEquals(contactService, contactService2);
    }

    @Mock
    ContactDao contactDao;

    @InjectMocks
    DefaultContactService contactService;

    @Test
    void saveNewContact() {
        Contact contact = new Contact("login", "surname", "name", "passport");
        when(contactDao.saveNewContact(contact)).thenReturn(contact);
        Contact savedContact = contactService.saveNewContact(contact);
        assertEquals(savedContact.getLogin(), "login");
        assertEquals(savedContact.getSurname(), "surname");
        assertEquals(savedContact.getName(), "name");
        assertEquals(savedContact.getPassport(), "passport");
    }

    @Test
    void getContactByLogin() {
        Contact contact = new Contact("login", "surname", "name", "passport");
        when(contactDao.getContactByLogin(contact.getLogin())).thenReturn(contact);
        Contact savedContact = contactService.getContactByLogin(contact.getLogin());
        assertEquals(savedContact.getLogin(), "login");
        assertEquals(savedContact.getSurname(), "surname");
        assertEquals(savedContact.getName(), "name");
        assertEquals(savedContact.getPassport(), "passport");
    }

    @Test
    void getAllContacts() {
        Contact contact = new Contact("login", "surname", "name", "passport");
        when(contactDao.getAllContacts()).thenReturn(Collections.singletonList(contact));
        List<Contact> savedContactList = contactService.getAllContacts();
        for (Contact savedContact : savedContactList) {
            assertEquals(savedContact.getLogin(), "login");
            assertEquals(savedContact.getSurname(), "surname");
            assertEquals(savedContact.getName(), "name");
            assertEquals(savedContact.getPassport(), "passport");
        }
    }
}