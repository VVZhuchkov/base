package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.ContactEntity;
import com.github.vvzhuchkov.base.model.Contact;

public class ContactConverter {
    public static Contact fromEntity(ContactEntity contact) {
        if (contact == null) {
            return null;
        }
        return new Contact(
                contact.getLogin(),
                contact.getSurname(),
                contact.getName(),
                contact.getPassport());
    }

    public static ContactEntity toEntity(Contact contact) {
        if (contact == null) {
            return null;
        }
        final ContactEntity contactEntity = new ContactEntity();
        contactEntity.setLogin(contact.getLogin());
        contactEntity.setSurname(contact.getSurname());
        contactEntity.setName(contact.getName());
        contactEntity.setPassport(contact.getPassport());
        return contactEntity;
    }
}

