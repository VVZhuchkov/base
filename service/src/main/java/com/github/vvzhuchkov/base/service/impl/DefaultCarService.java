package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.AuthUserDao;
import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultAuthUserDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultCarDao;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.SecurityService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultCarService implements CarService {
    private CarDao carDao = DefaultCarDao.getInstance();
    private static volatile CarService instance;

    public static CarService getInstance() {
        CarService localInstance = instance;
        if (localInstance == null) {
            synchronized (CarService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultCarService();
                }
            }
        }
        return localInstance;
    }

    public Car getById(Long id){
    Car car = carDao.getById(id);
    if (car == null){
        return null;}
    else {
        return car;
    }
    }

    @Override
    public List<Car> getByLocation(String location){
        List<Car> listOfCars= carDao.getAllCars();
        List<Car> listOfCityCars = new ArrayList<>();
        for (Car car : listOfCars){
            if (car.getLocation().equals(location)){
                listOfCityCars.add(car);
            }
        }
        return listOfCityCars;
    }

    @Override
    public List<Car> getAllCars(){
        List<Car> listOfCars= carDao.getAllCars();
        return listOfCars;
    }

@Override
    public void saveNewCar(Car car){
        carDao.saveNewCar(car);
    }
}
