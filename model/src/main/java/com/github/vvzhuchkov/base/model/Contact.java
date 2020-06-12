package com.github.vvzhuchkov.base.model;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String login;
    private String surname;
    private String name;
    private String passport;
    private List<Deal> dealList;

    public Contact(String login, String surname, String name, String passport) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.passport = passport;
    }

    public Contact(String login, String surname, String name, String passport, List<Deal> dealList) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.passport = passport;
        this.dealList = dealList;
    }

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public List<Deal> getDealList() {
        return dealList;
    }

    public void setDealList(List<Deal> createdList) {
        if(this.dealList == null){
            List<Deal> dealList = new ArrayList<>();
            dealList.addAll(createdList);
            this.dealList = dealList;
        } else {
            this.dealList.addAll(createdList);
        }
    }

   /* public void setDealList(Deal deal) {
        if(this.dealList == null){
            List<Deal> dealList = new ArrayList<>();
            dealList.add(deal);
            this.dealList = dealList;
        } else {
            this.dealList.add(deal);
        }
    }*/
}
