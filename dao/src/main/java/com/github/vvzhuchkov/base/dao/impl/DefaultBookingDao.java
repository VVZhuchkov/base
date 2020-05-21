package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.BookingDao;
import com.github.vvzhuchkov.base.dao.converter.BookingConverter;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.Booking;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultBookingDao implements BookingDao {
    private static final Logger logOut = LoggerFactory.getLogger(DefaultBookingDao.class);
    public static volatile BookingDao instance;

    public static BookingDao getInstance() {
        BookingDao localInstance = instance;
        if (localInstance == null) {
            synchronized (BookingDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultBookingDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveBooking(Booking booking){
        BookingEntity bookingEntity = BookingConverter.toEntity(booking);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(bookingEntity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteBooking(Long number) {
       HibernateUtil.getSession().createQuery("delete from BookingEntity au where au.number = :number")
                    .setParameter("number", number)
                    .getSingleResult();
    }

    @Override
    public List<Booking> getAllBookings(){
        final List<BookingEntity> listOfBookings = (List<BookingEntity>) HibernateUtil.getSession().createQuery("from BookingEntity, CarEntity")
                .list();
        return listOfBookings.stream()
                .map(BookingConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getBookingsByLogin(String login) {
        List<BookingEntity> bookings= (List<BookingEntity>)HibernateUtil.getSession().
                createQuery("from BookingEntity where login=:login").
                setParameter("login", login).
                list();
    return bookings.stream().map(BookingConverter::fromEntity).collect(Collectors.toList());
    }

    @Override
    public Booking getBookingByNumber(Long number) {
        BookingEntity booking;
        try {
            booking = (BookingEntity) HibernateUtil.getSession().createQuery("from BookingEntity where number = :number")
                    .setParameter("number", number)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("Booking not found by number {}", number);
            booking = null;
        }
        return BookingConverter.fromEntity(booking);
    }
}