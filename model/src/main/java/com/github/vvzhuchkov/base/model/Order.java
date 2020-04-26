package com.github.vvzhuchkov.base.model;

public class Order {
    private String login;
    private Long id;

    public Order(String login, Long id) {
        this.login = login;
        this.id=id;
    }

    public String getLogin() {
        return login;
    }

    public Long getId(){
    return id;
    }
}
