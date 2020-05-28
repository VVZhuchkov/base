package com.github.vvzhuchkov.base.model;

public class Contact {
    private String login;
    private String surname;
    private String name;
    private String passport;

    public Contact(String login, String surname, String name, String passport) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.passport = passport;
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
}
