package com.github.vvzhuchkov.base.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contact")
public class ContactEntity {
    @Id
    private String login;
    private String surname;
    private String name;
    private String passport;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "history", joinColumns = {@JoinColumn(name = "login")},
            inverseJoinColumns = {@JoinColumn(name = "number")})
    private List<DealEntity> dealEntityList = new ArrayList<>();

    public ContactEntity() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public List<DealEntity> getDealEntityList() {
        return dealEntityList;
    }

    public void setDealEntityList(List<DealEntity> dealEntityList) {
        this.dealEntityList = dealEntityList;
    }
}
