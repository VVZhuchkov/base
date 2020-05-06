package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.AuthUser;

public interface SecurityService {
    AuthUser login (String login, String password);

    AuthUser registration(String login, String password, String email);

    void saveNewRegUser (AuthUser user);

}
