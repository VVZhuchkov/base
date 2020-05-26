package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.Booking;

import java.util.List;

public interface BookingDao {

    void saveBooking(Booking booking);

    void deleteBooking(Long delNumber);

    PaginationResult<BookingEntity> getBookingsByLogin(String login, Integer page);

    Booking getBookingByNumber(Long number);
}
