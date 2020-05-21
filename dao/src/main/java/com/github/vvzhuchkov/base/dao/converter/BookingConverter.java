package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.Booking;

public class BookingConverter {
    public static Booking fromEntity(BookingEntity booking) {
        if (booking == null) {
            return null;
        }
        return new Booking(
                booking.getNumber(),
                booking.getLogin(),
                booking.getId(),
                booking.getPickup(),
                booking.getDropoff(),
                booking.getDays());
    }

    public static BookingEntity toEntity(Booking booking) {
        if (booking == null) {
            return null;
        }
        final BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setNumber(booking.getNumber());
        bookingEntity.setLogin(booking.getLogin());
        bookingEntity.setId(booking.getId());
        bookingEntity.setPickup(booking.getPickup());
        bookingEntity.setDropoff(booking.getDropoff());
        bookingEntity.setDays(booking.getDays());
        return bookingEntity;
    }
}
