package com.github.vvzhuchkov.base.model;

public class RoleUser {
    private String login;
    private String role;
    private int rating;

    public RoleUser(String login, String role, int rating) {
        this.login = login;
        this.role = role;
        this.rating = rating;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public int getRating() { return rating; }
}
