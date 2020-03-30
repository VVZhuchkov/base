package com.github.vvzhuchkov.dao;

import com.github.vvzhuchkov.model.AuthUser;

public interface AuthUserDao {

    AuthUser getById(int id);

}
