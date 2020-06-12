package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.model.Request;

import java.util.List;

public interface CarService {

    Car getById(Long id);

    List<Car> getByLocation(String location);

    List<Car> getAllCars();

    Car saveNewCar(Car car);

    Car getAvailableCars(Car car, Request mainReq);
}
