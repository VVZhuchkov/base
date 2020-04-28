package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultPaymentDao implements PaymentDao {
    public static volatile PaymentDao instance;

    public static PaymentDao getInstance() {
        PaymentDao localInstance = instance;
        if (localInstance == null) {
            synchronized (PaymentDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultPaymentDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Payment getPaymentByLogin(String login) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.payment where login = ?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Payment(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getLong("id"),
                            rs.getDate("pickup"),
                            rs.getDate("dropoff"),
                            rs.getLong("total"),
                            rs.getBoolean("approval"));

                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
