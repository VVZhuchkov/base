package com.github.vvzhuchkov.base.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Deal {
        private Long number;
        private String login;
        private Long id;
        private LocalDate pickup;
        private LocalDate dropoff;
        private Long total;
        private String approval;
        private String comment;
        private Contact contact;
        private List<Contact> contactList;

    public Deal(Long number, String login, Long id, LocalDate pickup, LocalDate dropoff, Long total, String approval, String comment, Contact contact) {
        this.number = number;
        this.login = login;
        this.id = id;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.total = total;
        this.approval = approval;
        this.comment = comment;
        this.contact = contact;
    }

    public Deal(Long number, String login, Long id, LocalDate pickup, LocalDate dropoff, Long total, String approval, String comment, Contact contact, List<Contact> contactList) {
        this.number = number;
        this.login = login;
        this.id = id;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.total = total;
        this.approval = approval;
        this.comment = comment;
        this.contact = contact;
        this.contactList = contactList;
    }

    public Long getNumber() {
        return number;
    }

    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getPickup() {
        return pickup;
    }

    public LocalDate getDropoff() {
        return dropoff;
    }

    public Long getTotal() {
        return total;
    }

    public String getApproval() {
        return approval;
    }

    public String getComment() {
        return comment;
    }

    public Contact getContact() {
        return contact;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Contact contact) {
        if(this.contactList == null){
            List<Contact> contacts = new ArrayList<>();
            contacts.add(contact);
            this.contactList = contacts;
        } else {
            this.contactList.add(contact);
        }
    }
}
