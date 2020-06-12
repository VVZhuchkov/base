package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.dao.impl.PaginationResult;
import com.github.vvzhuchkov.base.model.Booking;

public interface BookingDao {

    Booking saveBooking(Booking booking);

    boolean deleteBooking(Long delNumber);

    PaginationResult<BookingEntity> getBookingsByLogin(String login, Integer page);

    Booking getBookingByNumber(Long number);
}
