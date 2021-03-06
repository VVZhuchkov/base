package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Contact;

import java.util.List;

public interface ContactService {

    Contact saveNewContact(Contact contact);

    Contact getContactByLogin(String login);

    List<Contact> getAllContacts();
}
