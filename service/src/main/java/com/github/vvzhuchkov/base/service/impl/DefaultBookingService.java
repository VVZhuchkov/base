package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.BookingDao;
import com.github.vvzhuchkov.base.dao.impl.PaginationResult;
import com.github.vvzhuchkov.base.dao.converter.BookingConverter;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.dao.impl.DefaultBookingDao;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.service.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean saveBooking(Booking booking) {
        List<Booking> listBookingsByLogin = bookingDao.getBookingsByLogin(booking.getLogin(), 1).getList().
                stream().map(BookingConverter::fromEntity).collect(Collectors.toList());
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
        return true;
    }

    @Override
    public boolean deleteBooking(Long delNumber) {
        return bookingDao.deleteBooking(delNumber);
    }

    @Override
    public PaginationResult<BookingEntity> getAllBookingsByLogin(String login, Integer page) {
        return bookingDao.getBookingsByLogin(login, page);
    }

    @Override
    public Booking getBookingByNumber(Long number) {
        return bookingDao.getBookingByNumber(number);
    }
}
