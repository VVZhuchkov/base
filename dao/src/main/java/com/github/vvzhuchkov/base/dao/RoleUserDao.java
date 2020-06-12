package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.RoleUser;

public interface RoleUserDao {

    String getRoleUserByLogin(String login);

    Long getRatingUserByLogin(String login);

    void deleteRoleUser(String login);

    RoleUser saveNewRoleUser(RoleUser roleUser);

}
