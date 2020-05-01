package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.*;
import com.github.vvzhuchkov.base.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    public static volatile AuthUserDao instance;

    @Override
    public AuthUser getByLogin(String login) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * FROM base.registration where login=?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AuthUser(
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("email"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static AuthUserDao getInstance() {
        AuthUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultAuthUserDao();
                }
            }
        }
        return localInstance;
    }

    public List<String> getAllAuthEmails() {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Connection connection = DataSource.getInstance().getConnection();
                 PreparedStatement ps = connection.prepareStatement("select base.registration.email from base.registration");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String email = rs.getString("email");
                    result.add(email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void saveNewRegUser(AuthUser user) {
        final String sql = "insert into registration(login, password, email) values(?,?,?)";
        setNewUserRole(user);
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNewUserRole(AuthUser user) {
        final String sql = "insert into role(login, role) values(?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, "user");
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}