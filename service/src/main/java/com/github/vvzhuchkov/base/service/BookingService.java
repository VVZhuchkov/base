package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.dao.impl.PaginationResult;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.Booking;

public interface BookingService {

    boolean saveBooking(Booking booking);

    boolean deleteBooking(Long delNumber);

    PaginationResult<BookingEntity> getAllBookingsByLogin(String login, Integer page);

    Booking getBookingByNumber(Long number);
}
