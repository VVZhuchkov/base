package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.BookingDao;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.dao.impl.PaginationResult;
import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.service.BookingService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultBookingServiceTest {

    @Test
    void getInstance() {
        BookingService bookingService = DefaultBookingService.getInstance();
        BookingService bookingService2 = DefaultBookingService.getInstance();
        assertEquals(bookingService, bookingService2);
    }

    @Mock
    BookingDao bookingDao;

    @InjectMocks
    DefaultBookingService bookingService;

/*    @Test
    void saveBooking() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Booking booking = new Booking(null, "login", 1L, pickup, dropoff, 1L);
        when(bookingDao.getBookingsByLogin("login", 1)).thenReturn(List<Booking> list);
        boolean savedBooking = bookingService.saveBooking(booking);
        assertTrue(savedBooking);
    }*/

    @Test
    void deleteBooking() {
        Booking booking = new Booking(1L, "login", null, null, null, 1L);
        when(bookingDao.deleteBooking(1L)).thenReturn(true);
        final boolean delete = bookingService.deleteBooking(booking.getNumber());
        assertTrue(delete);
    }

    @Test
    void getAllBookingsByLogin() {
        Session session = HibernateUtil.getSession();
        String login = "login";
        String sql = "from BookingEntity where login=:login";
        Query<BookingEntity> query = session.createQuery(sql, BookingEntity.class);
        query.setParameter("login", login);
        when(bookingDao.getBookingsByLogin("login", 2)).thenReturn(new PaginationResult<>(query, 2, 2, 5));
        PaginationResult paginationResult = bookingService.getAllBookingsByLogin("login", 2);
        assertEquals(paginationResult.getMaxResult(), 2);
    }

    @Test
    void getBookingByNumber() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        when(bookingDao.getBookingByNumber(1L)).thenReturn(new Booking(1L, "login", 1L, pickup, dropoff, 1L));
        Booking savedBooking = bookingService.getBookingByNumber(1L);
        assertNotNull(savedBooking);
        assertEquals(savedBooking.getPickup(), pickup);
        assertEquals(savedBooking.getDropoff(), dropoff);
        assertEquals(savedBooking.getLogin(), "login");
    }
}
