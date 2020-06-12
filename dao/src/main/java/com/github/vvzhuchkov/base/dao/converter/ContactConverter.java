package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.ContactEntity;
import com.github.vvzhuchkov.base.model.Contact;

import java.util.stream.Collectors;

public class ContactConverter {
    public static Contact fromEntity(ContactEntity contactEntity) {
        if (contactEntity == null) {
            return null;
        }
        return new Contact(
                contactEntity.getLogin(),
                contactEntity.getSurname(),
                contactEntity.getName(),
                contactEntity.getPassport(),
            null);
            /*    contactEntity.getDealEntityList().stream().map(DealConverter::fromEntity).
                        collect(Collectors.toList()));*/
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
    /*    contactEntity.setDealEntityList(contact.getDealList().stream()
                .map(DealConverter::toEntity).collect(Collectors.toList()));*/
        return contactEntity;
    }
}
