package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.model.Deal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultDealDaoTest {
    DealDao dealDao = DefaultDealDao.getInstance();
    CarDao carDao = DefaultCarDao.getInstance();

    @Test
    void getInstance() {
        DealDao dealDao = DefaultDealDao.getInstance();
        DealDao dealDao2 = DefaultDealDao.getInstance();
        assertEquals(dealDao, dealDao2);
    }

/*    @Test
    void saveDeal() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        final Car car = new Car(1L, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        Long carId = savedCar.getId();
        List<Contact> contactList = Collections.singletonList(new Contact("vzhuchkov", "Zhuchkov", "Vladimir", "54321", null));
        final Deal deal = new Deal(null, "login", carId, pickup, dropoff, 35L, "approved", "ok", null, contactList);
        Deal savedDeal = dealDao.saveDeal(deal);
        assertEquals(deal.getLogin(), savedDeal.getLogin());
        assertEquals(deal.getApproval(), savedDeal.getApproval());
        dealDao.deleteDeal(deal.getNumber());
        carDao.deleteCar(carId);
    }*/
}