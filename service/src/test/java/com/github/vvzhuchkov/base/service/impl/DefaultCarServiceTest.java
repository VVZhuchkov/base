package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultCarServiceTest {

    @Test
    void getInstance() {
        CarService carService = DefaultCarService.getInstance();
        CarService carService2 = DefaultCarService.getInstance();
        assertEquals(carService, carService2);
    }

    @Mock
    CarDao carDao;

    @InjectMocks
    DefaultCarService carService;

    @Test
    void getById(){
        when(carDao.getById(1L)).thenReturn(new Car(1L, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available"));
        Car savedCar = carService.getById(1L);
        assertEquals(savedCar.getPhoto(), "photo");
        assertEquals(savedCar.getBrand(), "Honda");
        assertEquals(savedCar.getLocation(), "Tokio");
        Car saveNonExistingCar = carService.getById(2L);
        assertNull(saveNonExistingCar);
    }

    @Test
    void getAllCars(){
        Car car = new Car(1L, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        when(carDao.getAllCars()).thenReturn(Collections.singletonList(car));
        List<Car> allSavedCars = carService.getAllCars();
        assertEquals(allSavedCars.size(), 1);
        assertEquals(allSavedCars.get(0).getPhoto(), "photo");
        assertEquals(allSavedCars.get(0).getBrand(), "Honda");
    }
    @Test
    void saveNewCar(){
        Car car = new Car(1L, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        when(carDao.saveNewCar(car)).thenReturn(car);
        Car savedCar = carService.saveNewCar(car);
        assertEquals(savedCar.getPhoto(), "photo");
        assertEquals(savedCar.getModel(), "Civic");
        assertEquals(savedCar.getLocation(), "Tokio");
    }

    @Test
    void getByLocation(){
        Car car = new Car(1L, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        when(carDao.getAllCars()).thenReturn(Collections.singletonList(car));
        List<Car> carList = carService.getByLocation(car.getLocation());
        assertNotNull(carList);
    }
}
