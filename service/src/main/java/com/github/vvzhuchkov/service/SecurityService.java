package com.github.vvzhuchkov.service;

import com.github.vvzhuchkov.model.AuthUser;

public interface SecurityService {
    AuthUser login (int id, String password);
}
