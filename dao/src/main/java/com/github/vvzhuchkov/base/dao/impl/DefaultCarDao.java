package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.dao.converter.CarConverter;
import com.github.vvzhuchkov.base.dao.entity.CarEntity;
import com.github.vvzhuchkov.base.model.Car;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultCarDao implements CarDao {
    private static final Logger logOut = LoggerFactory.getLogger(DefaultCarDao.class);
    public static volatile CarDao instance;

    public static CarDao getInstance() {
        CarDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CarDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultCarDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Car getById(Long id) {
        CarEntity car;
        try {
            car = (CarEntity) HibernateUtil.getSession().createQuery("from CarEntity au where au.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("Car not found by id {}", id);
            car = null;
        }
        return CarConverter.fromEntity(car);
    }

    @Override
    public List<Car> getAllCars() {
        final List<CarEntity> car = HibernateUtil.getSession().createQuery("from CarEntity")
                .list();
        return car.stream()
                .map(CarConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Car saveNewCar(Car car) {
        CarEntity carEntity = CarConverter.toEntity(car);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(carEntity);
        session.getTransaction().commit();
        session.close();
        return CarConverter.fromEntity(carEntity);
    }

    @Override
    public boolean deleteCar(Long id) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete CarEntity ce where ce.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }
}