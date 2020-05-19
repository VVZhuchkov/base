package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.AuthUserEntity;
import com.github.vvzhuchkov.base.model.AuthUser;

public class AuthUserConverter {
    public static AuthUser fromEntity(AuthUserEntity authUser) {
        if (authUser == null) {
            return null;
        }
        return new AuthUser(
                authUser.getLogin(),
                authUser.getPassword(),
                authUser.getEmail());
    }

    public static AuthUserEntity toEntity(AuthUser authUser) {
        if (authUser == null) {
            return null;
        }
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setLogin(authUser.getLogin());
        authUserEntity.setPassword(authUser.getPassword());
        authUserEntity.setEmail(authUser.getEmail());
        return authUserEntity;
    }
}

