package com.github.vvzhuchkov.dao;

import com.github.vvzhuchkov.model.AuthUser;

import java.util.Map;

public interface AuthUserDao {

   Map<Integer, AuthUser> getEmployees();

}
