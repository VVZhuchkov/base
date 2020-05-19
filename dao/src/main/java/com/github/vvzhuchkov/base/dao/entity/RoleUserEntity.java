package com.github.vvzhuchkov.base.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleUserEntity {
    private String login;
    private String role;
    private Long rating;

    public RoleUserEntity() {
    }

    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
