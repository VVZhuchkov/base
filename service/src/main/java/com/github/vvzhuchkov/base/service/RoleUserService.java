package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.AuthUser;

public interface RoleUserService {

    String getRoleUserByLogin(String login);

    Long getRatingUserByLogin(String login);
}
