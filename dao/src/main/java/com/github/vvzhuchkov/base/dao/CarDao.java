package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.dao.entity.CarEntity;
import com.github.vvzhuchkov.base.model.Car;

import java.util.List;

public interface CarDao {

    Car getById(Long id);

    List<Car> getAllCars();

    Car saveNewCar(Car car);

    boolean deleteCar(Long id);
}
