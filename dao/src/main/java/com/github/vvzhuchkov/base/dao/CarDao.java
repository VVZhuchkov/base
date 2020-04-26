package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Car;

import java.util.List;

public interface CarDao {

    Car getById(Long id);

    List<Car> getAllCars();

    //void addNewCar();

    //void deleteCar();

}
