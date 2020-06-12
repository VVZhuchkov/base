package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.BookingDao;
import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.model.Car;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
//100%
class DefaultBookingDaoTest {
    BookingDao bookingDao = DefaultBookingDao.getInstance();
    CarDao carDao = DefaultCarDao.getInstance();

    @Test
    void getInstance() {
        BookingDao bookingDao = DefaultBookingDao.getInstance();
        BookingDao bookingDao2 = DefaultBookingDao.getInstance();
        assertEquals(bookingDao, bookingDao2);
    }

    @Test
    void saveBooking() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        final Car car = new Car(null, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        Long carId = savedCar.getId();
        final Booking booking = new Booking(null, "login", car.getId(), pickup, dropoff, 1L);
        final Booking savedBooking = bookingDao.saveBooking(booking);
        Long bookingNumber = savedBooking.getNumber();
        assertEquals(savedBooking.getLogin(), booking.getLogin());
        bookingDao.deleteBooking(bookingNumber);
        assertNull(bookingDao.getBookingByNumber(booking.getNumber()));
        carDao.deleteCar(carId);
        assertNull(carDao.getById(carId));
    }


    @Test
    void getBookingByNumber() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        final Car car = new Car(null, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        Long carId = savedCar.getId();
        final Booking booking = new Booking(null, "login", carId, pickup, dropoff, 1L);
        final Booking savedBooking = bookingDao.saveBooking(booking);
        Long bookingNumber = savedBooking.getNumber();
        assertEquals(booking.getNumber(), bookingDao.getBookingByNumber(booking.getNumber()));
        bookingDao.deleteBooking(bookingNumber);
        assertNull(bookingDao.getBookingByNumber(bookingNumber));
        carDao.deleteCar(carId);
        assertNull(carDao.getById(carId));
    }

    @Test
    void deleteBooking() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        final Car car = new Car(null, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        Long carId = savedCar.getId();
        final Booking booking = new Booking(null, "login", carId, pickup, dropoff, 1L);
        final Booking savedBooking = bookingDao.saveBooking(booking);
        Long bookingNumber = savedBooking.getNumber();
        assertNotNull(bookingDao.getBookingByNumber(bookingNumber));
        bookingDao.deleteBooking(bookingNumber);
        assertNull(bookingDao.getBookingByNumber(bookingNumber));
        assertNotNull(carDao.getById(carId));
        carDao.deleteCar(carId);
        assertNull(bookingDao.getBookingByNumber(carId));
    }

    @Test
    void getBookingsByLogin() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        final Car car = new Car(null, "photo", "Honda", "Civic", 2011L, "1,8", 25L, "Tokio", "available");
        final Car savedCar = carDao.saveNewCar(car);
        Long carId = savedCar.getId();
        final Booking booking = new Booking(null, "login", carId, pickup, dropoff, 1L);
        final Booking savedBooking = bookingDao.saveBooking(booking);
        Long bookingNumber = savedBooking.getNumber();
        Session session = HibernateUtil.getSession();
        String sql = "from BookingEntity where login=:login";
        Query<BookingEntity> query = session.createQuery(sql, BookingEntity.class);
        query.setParameter("login", savedBooking.getLogin());
        PaginationResult<BookingEntity> result = new PaginationResult<>(query, 1, 2, 5);
        assertEquals(bookingDao.getBookingsByLogin("login", 1).getTotalRecords(), result.getTotalRecords());
        bookingDao.deleteBooking(bookingNumber);
        assertNull(bookingDao.getBookingByNumber(bookingNumber));
        carDao.deleteCar(carId);
        assertNull(carDao.getById(carId));
    }


    @AfterClass
    public void cleanUp() {
        HibernateUtil.closeEMFactory();
    }
}
