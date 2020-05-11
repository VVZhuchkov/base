package com.github.vvzhuchkov.base.model;

public class Restitution {
    private Long number;
    private String login;
    private String comment;
    private Long rating;
    private String status;

    public Restitution(Long number, String login, String comment, Long rating, String status) {
        this.number = number;
        this.login = login;
        this.comment = comment;
        this.rating = rating;
        this.status=status;
    }

    public Long getNumber() {
        return number;
    }

    public String getLogin() {
        return login;
    }

    public String getComment() {
        return comment;
    }

    public Long getRating(){
        return rating;
    }

    public String getStatus() {
        return status;
    }
}
