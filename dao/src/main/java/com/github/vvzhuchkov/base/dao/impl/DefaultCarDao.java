package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.DataSource;
import com.github.vvzhuchkov.base.model.Car;

import java.sql.*;
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
             PreparedStatement ps = connection.prepareStatement("select * FROM base.car where base.car.id=?")) {
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
             PreparedStatement ps = connection.prepareStatement("select * from base.car order by base.car.id asc");
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

    public void saveNewCar(Car car){
        final String sql = "insert into base.car(photo, brand, model, year, engine, location, price, availability) values(?,?,?,?,?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, car.getPhoto());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getModel());
            ps.setLong(4, car.getYear());
            ps.setString(5, car.getEngine());
            ps.setString(6, car.getLocation());
            ps.setLong(7, car.getPrice());
            ps.setString(8, car.getAvailability());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}