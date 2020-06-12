package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.util.HibernateUtil;
import com.github.vvzhuchkov.base.dao.BookingDao;
import com.github.vvzhuchkov.base.dao.converter.BookingConverter;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.Booking;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;

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
    public Booking saveBooking(Booking booking) {
        BookingEntity bookingEntity = BookingConverter.toEntity(booking);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(bookingEntity);
        session.getTransaction().commit();
        session.close();
        return BookingConverter.fromEntity(bookingEntity);
    }

    @Override
    public boolean deleteBooking(Long number) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete BookingEntity au where au.number = :number")
                .setParameter("number", number)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public PaginationResult<BookingEntity> getBookingsByLogin(String login, Integer page) {
        Session session = HibernateUtil.getSession();
        String sql = "from BookingEntity where login=:login";
        Query<BookingEntity> query = session.createQuery(sql, BookingEntity.class);
        query.setParameter("login", login);
        int maxResult = 2;
        int maxNavigationResult = 5;
        PaginationResult<BookingEntity> result = new PaginationResult<>(query, page, maxResult, maxNavigationResult);
        session.close();
        return result;
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