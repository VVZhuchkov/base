package com.github.vvzhuchkov.base.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleUserEntity {
    @Id
    private String login;
    private String role;
    private Long rating;

    public RoleUserEntity() {
    }

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
