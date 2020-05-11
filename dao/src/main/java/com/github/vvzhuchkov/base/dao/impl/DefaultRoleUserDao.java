package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.dao.RoleUserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultRoleUserDao implements RoleUserDao {
    public static volatile RoleUserDao instance;

    public static RoleUserDao getInstance() {
        RoleUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RoleUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRoleUserDao();
                }
            }
        }
        return localInstance;
    }

    public String getRoleUserByLogin(String login){
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select base.role.role FROM base.role where login=?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getRatingUserByLogin(String login){
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select base.role.rating FROM base.role where login=?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("rating");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
