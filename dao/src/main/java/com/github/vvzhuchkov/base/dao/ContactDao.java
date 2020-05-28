package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Contact;

public interface ContactDao {
    void saveNewContact(Contact contact);

    Contact getContactByLogin(String login);
}
