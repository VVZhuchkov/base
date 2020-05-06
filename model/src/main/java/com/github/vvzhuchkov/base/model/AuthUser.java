package com.github.vvzhuchkov.base.model;

public class AuthUser {
    private String login;
    private String password;
    private String email;

    public AuthUser (String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
