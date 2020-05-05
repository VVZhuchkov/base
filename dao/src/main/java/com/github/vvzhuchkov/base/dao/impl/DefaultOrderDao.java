package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.dao.OrderDao;
import com.github.vvzhuchkov.base.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDao implements OrderDao {
    public static volatile OrderDao instance;

    public static OrderDao getInstance() {
        OrderDao localInstance = instance;
        if (localInstance == null) {
            synchronized (OrderDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultOrderDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveOrder(Order order) {
        final String sql = "insert into base.order(login, id, pickup, dropoff, days) values(?,?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, order.getLogin());
            ps.setLong(2, order.getId());
            ps.setDate(3, Date.valueOf(order.getPickup()));
            ps.setDate(4, Date.valueOf(order.getDropoff()));
            ps.setLong(5, order.getDays());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrder(Long number) {
        final String sql = "delete from base.order where number=?";
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

    @Override
    public List<Order> getAllOrders() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select base.order.*, base.car.*, base.offer.* from base.order join base.car on order.id = car.id join base.offer on car.id = offer.id;");
             ResultSet rs = ps.executeQuery()) {
            final ArrayList<Order> listOfOrders = new ArrayList<>();
            while (rs.next()) {
                final Order order = new Order(
                        rs.getLong("number"),
                        rs.getString("login"),
                        rs.getLong("id"),
                        rs.getString("photo"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getLong("year"),
                        rs.getString("engine"),
                        rs.getLong("price"),
                        rs.getString("location"),
                        rs.getString("availability"),
                        rs.getDate("pickup").toLocalDate(),
                        rs.getDate("dropoff").toLocalDate(),
                        rs.getLong("days"));
                listOfOrders.add(order);
            }
            return listOfOrders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getOrderByLogin(String login) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select base.order.*, base.car.*, base.offer.* from base.order join base.car on order.id = car.id join base.offer on car.id = offer.id where login=?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                final ArrayList listOfOrdersByLogin = new ArrayList();
                while (rs.next()) {
                    final Order order = new Order(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getLong("id"),
                            rs.getString("photo"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getLong("year"),
                            rs.getString("engine"),
                            rs.getLong("price"),
                            rs.getString("location"),
                            rs.getString("availability"),
                            rs.getDate("pickup").toLocalDate(),
                            rs.getDate("dropoff").toLocalDate(),
                            rs.getLong("days"));
                    listOfOrdersByLogin.add(order);
                }
                return listOfOrdersByLogin;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrderByNumber(Long number) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select base.order.*, base.car.*, base.offer.* from base.order join base.car on order.id = car.id join base.offer on car.id = offer.id where number=?")) {
            ps.setLong(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getLong("number"),
                            rs.getString("login"),
                            rs.getLong("id"),
                            rs.getString("photo"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getLong("year"),
                            rs.getString("engine"),
                            rs.getLong("price"),
                            rs.getString("location"),
                            rs.getString("availability"),
                            rs.getDate("pickup").toLocalDate(),
                            rs.getDate("dropoff").toLocalDate(),
                            rs.getLong("days"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}