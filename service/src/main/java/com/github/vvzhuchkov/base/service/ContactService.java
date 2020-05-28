package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Contact;

public interface ContactService {

    void saveNewContact(Contact contact);

    Contact getContactByLogin(String login);
}
