package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.dao.PaginationResult;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.Booking;

import java.util.List;

public interface BookingService {

    void saveBooking(Booking booking);

    void deleteBooking(Long delNumber);

    PaginationResult<BookingEntity> getAllBookingsByLogin(String login, Integer page);

    Booking getBookingByNumber(Long number);
}
