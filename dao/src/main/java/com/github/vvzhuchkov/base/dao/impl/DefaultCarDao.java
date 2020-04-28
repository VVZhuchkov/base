package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultCarDao implements CarDao {
    public static volatile CarDao instance;

    public static CarDao getInstance() {
        CarDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultCarDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Car getById(Long id) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select base.car.*, base.offer.price, " +
                     "base.offer.location, base.offer.availability from base.car " +
                     "join base.offer on car.id = offer.id where base.car.id=?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Car(
                            rs.getLong("id"),
                            rs.getString("photo"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getLong("year"),
                            rs.getString("engine"),
                            rs.getLong("price"),
                            rs.getString("location"),
                            rs.getString("availability"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAllCars() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement("select base.car.*, base.offer.price, " +
                     "base.offer.location, base.offer.availability from base.car join base.offer on car.id = offer.id " +
                     "order by base.offer.location asc");
             ResultSet rs = ps.executeQuery()) {
            final ArrayList<Car> listOfCars = new ArrayList<>();
            while (rs.next()) {
                final Car car = new Car(
                        rs.getLong("id"),
                        rs.getString("photo"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getLong("year"),
                        rs.getString("engine"),
                        rs.getLong("price"),
                        rs.getString("location"),
                        rs.getString("availability"));
                listOfCars.add(car);
            }
            return listOfCars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}