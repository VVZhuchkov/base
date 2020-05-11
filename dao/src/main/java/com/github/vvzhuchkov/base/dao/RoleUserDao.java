package com.github.vvzhuchkov.base.dao;

public interface RoleUserDao {

    String getRoleUserByLogin(String login);

    Long getRatingUserByLogin(String login);
}
