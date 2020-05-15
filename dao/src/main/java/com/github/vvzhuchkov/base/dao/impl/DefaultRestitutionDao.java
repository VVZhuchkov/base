package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.dao.RestitutionDao;
import com.github.vvzhuchkov.base.model.Restitution;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultRestitutionDao implements RestitutionDao {
    public static volatile RestitutionDao instance;

    public static RestitutionDao getInstance() {
        RestitutionDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RestitutionDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRestitutionDao();
                }
            }
        }
        return localInstance;
    }

    public void saveReturn(Restitution restitution){
        final String sql = "insert into base.restitution(number, login, comment, rating, status) values(?,?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, restitution.getNumber());
            ps.setString(2, restitution.getLogin());
            ps.setString(3, restitution.getComment());
            ps.setLong(4, restitution.getRating());
            ps.setString(5,restitution.getStatus());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Restitution getRestitutionsByNumber(Long number){
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.restitution where number=? order by base.restitution.number desc ")) {
            ps.setLong(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Restitution(
                            rs.getLong("number"),
                    rs.getString("login"),
                    rs.getString("comment"),
                    rs.getLong("rating"),
                    rs.getString("status"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Restitution> getRestitutionsByLogin(String login) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.restitution where login = ?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                final ArrayList<Restitution> listOfRestitutionsByLogin = new ArrayList<>();
                while (rs.next()) {
                    final Restitution restitution = new Restitution(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getString("comment"),
                            rs.getLong("rating"),
                            rs.getString("status"));
                    listOfRestitutionsByLogin.add(restitution);
                }
                return listOfRestitutionsByLogin;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Restitution> getAllRestitutions() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.restitution")) {
            try (ResultSet rs = ps.executeQuery()) {
                final ArrayList<Restitution> listOfRestitutions = new ArrayList();
                while (rs.next()) {
                    final Restitution restitution = new Restitution(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getString("comment"),
                            rs.getLong("rating"),
                            rs.getString("status"));
                    listOfRestitutions.add(restitution);
                }
                return listOfRestitutions;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updStatus(Long number, String status){
        final String sql = "update base.restitution SET status=? where restitution.number = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, status);
            ps.setLong(2, number);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updStaComm(Long number, String status, String comment){
        final String sql = "update base.restitution SET status=?, comment=? where restitution.number = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, status);
            ps.setString(2, comment);
            ps.setLong(3, number);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
