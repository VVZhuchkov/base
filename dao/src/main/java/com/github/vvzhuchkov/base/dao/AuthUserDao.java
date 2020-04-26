package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.AuthUser;

import java.util.List;

public interface AuthUserDao {

    AuthUser getByLogin(String login);

    List<String> getAllAuthEmails();

    void saveNewRegUser (AuthUser user);

    void setNewUserRole(AuthUser user);
}
