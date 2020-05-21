package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Booking;

import java.util.List;

public interface BookingService {

    void saveBooking(Booking booking);

    void deleteBooking(Long delNumber);

    List<Booking> getAllBookingsByLogin(String login);
}
