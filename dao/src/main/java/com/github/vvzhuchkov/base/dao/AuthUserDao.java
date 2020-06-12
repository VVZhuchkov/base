package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.RoleUser;

import java.util.List;

public interface AuthUserDao {

    AuthUser getByLogin(String login);

    List<String> getAllAuthEmails();

    AuthUser saveNewRegUser(AuthUser user);

    RoleUser setNewUserRole(String login);

    void deleteUser(String login);
}
