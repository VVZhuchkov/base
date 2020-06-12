package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Contact;

import java.util.List;

public interface ContactDao {

    Contact saveNewContact(Contact contact);

    Contact getContactByLogin(String login);

    List<Contact> getAllContacts();

    void deleteContact(String login);
}
