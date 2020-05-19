package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.RoleUserEntity;
import com.github.vvzhuchkov.base.model.RoleUser;

public class RoleUserConverter {
    public static RoleUser fromEntity(RoleUserEntity roleUser) {
        if (roleUser == null) {
            return null;
        }
        return new RoleUser(
                roleUser.getLogin(),
                roleUser.getRole(),
                roleUser.getRating());
    }

    public static RoleUserEntity toEntity(RoleUser roleUser) {
        if (roleUser == null) {
            return null;
        }
        final RoleUserEntity roleUserEntity = new RoleUserEntity();
        roleUserEntity.setLogin(roleUser.getLogin());
        roleUserEntity.setRole(roleUser.getRole());
        roleUserEntity.setRating(roleUser.getRating());
        return roleUserEntity;
    }
}
