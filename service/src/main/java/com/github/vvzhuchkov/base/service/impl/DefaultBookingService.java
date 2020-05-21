package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.BookingDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultBookingDao;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.service.BookingService;

import java.util.ArrayList;
import java.util.List;

public class DefaultBookingService implements BookingService {

    private BookingDao bookingDao = DefaultBookingDao.getInstance();

    private static volatile BookingService instance;

    public static BookingService getInstance() {
        BookingService localInstance = instance;
        if (localInstance == null) {
            synchronized (BookingService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultBookingService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveBooking(Booking booking) {
        List<Booking> listBookingsByLogin = bookingDao.getBookingsByLogin(booking.getLogin());
        List<Long> listOfIdsByLogin = new ArrayList<>();
        boolean isFlag = true;
        for (Booking existBooking : listBookingsByLogin) {
            listOfIdsByLogin.add(existBooking.getId());
        }
        if (listOfIdsByLogin.contains(booking.getId())) {
            for (Booking existBooking : listBookingsByLogin) {
                if ((existBooking.getId() == booking.getId()) && (!(booking.getDropoff().isBefore(existBooking.getPickup()) ||
                        booking.getPickup().isAfter(existBooking.getDropoff())))) {
                    isFlag = false;
                    break;
                }
            }
        }

        if (isFlag) {
            bookingDao.saveBooking(booking);
        }
    }

    @Override
    public void deleteBooking(Long delNumber) {
        bookingDao.deleteBooking(delNumber);
    }

    @Override
    public List<Booking> getAllBookingsByLogin(String login) {
        return bookingDao.getBookingsByLogin(login);
    }

}
