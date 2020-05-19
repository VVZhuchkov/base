package com.github.vvzhuchkov.base.model;

public class RoleUser {
    private String login;
    private String role;
    private Long rating;

    public RoleUser(String login, String role, Long rating) {
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

    public Long getRating() { return rating; }
}
