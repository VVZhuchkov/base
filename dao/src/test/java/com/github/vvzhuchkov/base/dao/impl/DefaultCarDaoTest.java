package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.model.Car;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//100%
class DefaultCarDaoTest {
    CarDao carDao = DefaultCarDao.getInstance();

    @Test
    void getInstance() {
        CarDao carDao = DefaultCarDao.getInstance();
        CarDao carDao2 = DefaultCarDao.getInstance();
        assertEquals(carDao, carDao2);
    }

    @Test
    void getById() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        final Car car = new Car(null, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        Long carId = savedCar.getId();
        assertEquals(savedCar.getPhoto(), car.getPhoto());
        assertEquals(savedCar.getPrice(), car.getPrice());
        carDao.deleteCar(carId);
        assertNull(carDao.getById(carId));
    }

    @Test
    void getAllCars() {
        final Car car = new Car(null, "photo", "Honda1", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car car2 = new Car(null, "photo", "Honda2", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car car3 = new Car(null, "photo", "Honda3", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        final Car savedCar2 = carDao.saveNewCar(car2);
        final Car savedCar3 = carDao.saveNewCar(car3);
        List<Car> savedCarList = carDao.getAllCars();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        carList.add(car2);
        carList.add(car3);
        for (int i = 0; i < savedCarList.size(); i++) {
            assertEquals(carList.get(i).getEngine(), savedCarList.get(i).getEngine());
            assertEquals(carList.get(i).getBrand(), savedCarList.get(i).getBrand());
            assertEquals(carList.get(i).getPrice(), savedCarList.get(i).getPrice());
        }
        carDao.deleteCar(savedCar.getId());
        carDao.deleteCar(savedCar2.getId());
        carDao.deleteCar(savedCar3.getId());
    }

    @Test
    void saveNewCar() {
        final Car car = new Car(null, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        assertEquals(savedCar.getBrand(), car.getBrand());
        assertEquals(savedCar.getPhoto(), car.getPhoto());
        carDao.deleteCar(savedCar.getId());
    }

    @Test
    void deleteCar() {
        final Car car = new Car(null, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        Long carId = savedCar.getId();
        assertNotNull(carDao.getById(carId));
        carDao.deleteCar(carId);
        assertNull(carDao.getById(carId));
    }

    @AfterClass
    public void cleanUp() {
        HibernateUtil.closeEMFactory();
    }
}
