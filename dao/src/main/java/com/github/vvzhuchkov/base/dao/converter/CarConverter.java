package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.CarEntity;
import com.github.vvzhuchkov.base.model.Car;

import java.util.stream.Collectors;

public class CarConverter {
    public static Car fromEntity(CarEntity carEntity) {
        if (carEntity == null) {
            return null;
        }
        return new Car(
                carEntity.getId(),
                carEntity.getPhoto(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getYear(),
                carEntity.getEngine(),
                carEntity.getPrice(),
                carEntity.getLocation(),
                carEntity.getAvailability());
    }

    public static CarEntity toEntity(Car car) {
        if (car == null) {
            return null;
        }
        final CarEntity carEntity = new CarEntity();
        carEntity.setId(car.getId());
        carEntity.setPhoto(car.getPhoto());
        carEntity.setBrand(car.getBrand());
        carEntity.setModel(car.getModel());
        carEntity.setYear(car.getYear());
        carEntity.setEngine(car.getEngine());
        carEntity.setPrice(car.getPrice());
        carEntity.setLocation(car.getLocation());
        carEntity.setAvailability(car.getAvailability());
        return carEntity;
    }
}