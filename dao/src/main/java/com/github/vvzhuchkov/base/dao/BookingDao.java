package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Booking;

import java.util.List;

public interface BookingDao {

    void saveBooking(Booking booking);

    void deleteBooking(Long delNumber);

    List<Booking> getAllBookings();

    List<Booking> getBookingsByLogin(String login);

    Booking getBookingByNumber(Long number);
}
