package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public List<Payment> getPaymentsByLogin(String login) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.payment where login = ?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                final ArrayList<Payment> listOfPaymentsByLogin = new ArrayList<>();
                while (rs.next()) {
                    final Payment payment = new Payment(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getString("surname"),
                            rs.getString("name"),
                            rs.getString("passport"),
                            rs.getLong("id"),
                            rs.getDate("pickup").toLocalDate(),
                            rs.getDate("dropoff").toLocalDate(),
                            rs.getLong("total"),
                            rs.getString("approval"),
                            rs.getString("comment"));
                    listOfPaymentsByLogin.add(payment);
                }
                return listOfPaymentsByLogin;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment getPaymentByNumber(Long number) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.payment where number = ?")) {
            ps.setLong(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Payment(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getString("surname"),
                            rs.getString("name"),
                            rs.getString("passport"),
                            rs.getLong("id"),
                            rs.getDate("pickup").toLocalDate(),
                            rs.getDate("dropoff").toLocalDate(),
                            rs.getLong("total"),
                            rs.getString("approval"),
                            rs.getString("comment"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.payment")) {
            try (ResultSet rs = ps.executeQuery()) {
                final ArrayList<Payment> listOfPayments = new ArrayList();
                while (rs.next()) {
                    final Payment payment = new Payment(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getString("surname"),
                            rs.getString("name"),
                            rs.getString("passport"),
                            rs.getLong("id"),
                            rs.getDate("pickup").toLocalDate(),
                            rs.getDate("dropoff").toLocalDate(),
                            rs.getLong("total"),
                            rs.getString("approval"),
                            rs.getString("comment"));
                    listOfPayments.add(payment);
                }
                return listOfPayments;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveContPayment(Payment payment) {
        final String sql = "insert into base.payment(number, login, surname, name, passport, id, pickup, dropoff, total, approval, comment) values(?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, payment.getNumber());
            ps.setString(2, payment.getLogin());
            ps.setString(3, payment.getSurname());
            ps.setString(4, payment.getName());
            ps.setString(5, payment.getPassport());
            ps.setLong(6, payment.getId());
            ps.setDate(7, Date.valueOf(payment.getPickup()));
            ps.setDate(8, Date.valueOf(payment.getDropoff()));
            ps.setLong(9, payment.getTotal());
            ps.setString(10, payment.getApproval());
            ps.setString(11, payment.getComment());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrder(Long number) {
        final String sql = "delete from base.payment where number=?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, number);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateComment(String newComment) {
        final String sql = "update base.payment SET comment=?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, newComment);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateApproval(String newApp) {
        final String sql = "update base.payment SET approval=?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, newApp);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
