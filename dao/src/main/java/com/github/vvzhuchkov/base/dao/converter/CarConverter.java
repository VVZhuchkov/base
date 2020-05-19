package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.CarEntity;
import com.github.vvzhuchkov.base.model.Car;

public class CarConverter {
    public static Car fromEntity(CarEntity car) {
        if (car == null) {
            return null;
        }
        return new Car(
                car.getId(),
                car.getPhoto(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getEngine(),
                car.getPrice(),
                car.getLocation(),
                car.getAvailability());
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
