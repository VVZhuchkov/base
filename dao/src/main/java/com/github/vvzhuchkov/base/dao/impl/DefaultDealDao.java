package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultDealDao implements DealDao {
    public static volatile DealDao instance;

    public static DealDao getInstance() {
        DealDao localInstance = instance;
        if (localInstance == null) {
            synchronized (DealDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultDealDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveDeal(Payment payment){
        final String sql = "insert into base.deal(number, login, surname, name, passport, id, pickup, dropoff, total, approval, comment) values(?,?,?,?,?,?,?,?,?,?,?)";
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

    @Override
    public List<Payment> getDealsByLogin(String login){
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.deal where login=? order by base.deal.number desc ")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                final ArrayList listOfDealsByLogin = new ArrayList();
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
                    listOfDealsByLogin.add(payment);
                }
                return listOfDealsByLogin;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Payment> getAllDeals(){
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.deal order by base.deal.number desc")) {
            try (ResultSet rs = ps.executeQuery()) {
                final ArrayList<Payment> listOfDeals = new ArrayList();
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
                    listOfDeals.add(payment);
                }
                return listOfDeals;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}