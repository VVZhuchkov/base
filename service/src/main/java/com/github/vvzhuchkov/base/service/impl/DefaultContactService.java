package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.ContactDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultContactDao;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.service.ContactService;

public class DefaultContactService implements ContactService {
    private ContactDao contactDao = DefaultContactDao.getInstance();
    private static volatile ContactService instance;

    public static ContactService getInstance() {
        ContactService localInstance = instance;
        if (localInstance == null) {
            synchronized (ContactService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultContactService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveNewContact(Contact contact){
        contactDao.saveNewContact(contact);
    }

   @Override
    public  Contact getContactByLogin(String login){
        return contactDao.getContactByLogin(login);
   }
}
