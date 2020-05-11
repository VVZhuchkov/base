package com.github.vvzhuchkov.base.service;

public interface RoleUserService {

    String getRoleUserByLogin(String login);

    Long getRatingUserByLogin(String login);
}
