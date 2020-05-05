package com.github.vvzhuchkov.base.model;

public class RoleUser {
    private String login;
    private String role;

    public RoleUser(String login, String role) {
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }
}
