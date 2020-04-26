package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.dao.OrderDao;
import com.github.vvzhuchkov.base.model.Car;
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
    public void saveOrder(String login, Car car) {
        final String sql = "insert into base.order(login, id) values(?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, login);
            ps.setLong(2, car.getId());
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
             PreparedStatement ps = connection.prepareStatement("select * from base.order");
            ResultSet rs = ps.executeQuery()) {
            final ArrayList<Order> listOfOrders = new ArrayList<>();
            while (rs.next()) {
                    final Order order = new Order(
                            rs.getString("login"),
                            rs.getLong("id"));
                listOfOrders.add(order);
                }
                    return listOfOrders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrderById(Long id) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from base.order where id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getString("login"),
                            rs.getLong("id"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
