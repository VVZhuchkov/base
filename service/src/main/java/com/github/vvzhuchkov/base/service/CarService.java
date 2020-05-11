package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Car;

import java.util.List;

public interface CarService {

    Car getById(Long id);

    List<Car> getByLocation(String location);

    List<Car> getAllCars();

    void saveNewCar(Car car);
}
