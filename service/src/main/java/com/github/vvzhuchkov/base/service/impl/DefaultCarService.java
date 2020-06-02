package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.AuthUserDao;
import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultAuthUserDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultCarDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultDealDao;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.SecurityService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultCarService implements CarService {
    private CarDao carDao = DefaultCarDao.getInstance();
    private DealDao dealDao = DefaultDealDao.getInstance();
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

    public Car getById(Long id) {
        Car car = carDao.getById(id);
        if (car == null) {
            return null;
        } else {
            return car;
        }
    }

    @Override
    public List<Car> getByLocation(String location) {
        List<Car> listOfCars = carDao.getAllCars();
        List<Car> listOfCityCars = new ArrayList<>();
        for (Car car : listOfCars) {
            if (car.getLocation().equals(location)) {
                listOfCityCars.add(car);
            }
        }
        return listOfCityCars;
    }

    @Override
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Override
    public void saveNewCar(Car car) {
        carDao.saveNewCar(car);
    }

    @Override
    public List<Car> getAvailableCars(Request mainReq) {
        List<Car> cars = getByLocation(mainReq.getLocation());
        List<Deal> deals = dealDao.getAllDeals();
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            for (Deal deal : deals) {
                if ((car.getId() == deal.getId()) && (!(mainReq.getDropoff().isBefore(deal.getPickup()) ||
                        mainReq.getPickup().isAfter(deal.getDropoff())))) {
                    continue;
                } else {
                    availableCars.add(car);
                }
            }
        }
        return availableCars;
    }
}
