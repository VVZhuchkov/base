package com.github.vvzhuchkov.base.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "registration")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuthUserEntity {
    @Id
    private String login;
    private String password;
    private String email;

    public AuthUserEntity() {
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
