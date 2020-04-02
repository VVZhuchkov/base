package com.github.vvzhuchkov.service;

import com.github.vvzhuchkov.model.AuthUser;

import java.util.List;
import java.util.Map;

public interface SecurityService {
    AuthUser login (int id, String password);

  Map<Integer, AuthUser> getEmployees();
}
