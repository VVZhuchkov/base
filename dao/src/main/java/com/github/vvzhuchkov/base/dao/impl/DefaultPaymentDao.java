package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.dao.converter.PaymentConverter;
import com.github.vvzhuchkov.base.dao.entity.PaymentEntity;
import com.github.vvzhuchkov.base.model.Payment;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultPaymentDao implements PaymentDao {
    private static final Logger logOut = LoggerFactory.getLogger(DefaultPaymentDao.class);
    public static volatile PaymentDao instance;

    public static PaymentDao getInstance() {
        PaymentDao localInstance = instance;
        if (localInstance == null) {
            synchronized (PaymentDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultPaymentDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Payment> getPaymentsByLogin(String login){
        final List<PaymentEntity> listOfPaymentsByLogin = HibernateUtil.getSession().createQuery("from PaymentEntity where login=:login")
                .setParameter("login", login)
                .list();
        return listOfPaymentsByLogin.stream()
                .map(PaymentConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Payment getPaymentByNumber(Long number){
        PaymentEntity payment;
        try {
            payment = (PaymentEntity) HibernateUtil.getSession().createQuery("from PaymentEntity pe where pe.number = :number")
                    .setParameter("number", number)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("Payment not found by number {}", number);
            payment = null;
        }
        return PaymentConverter.fromEntity(payment);
    }

    @Override
    public List<Payment> getAllPayments(){
        final List<PaymentEntity> listOfPayment = HibernateUtil.getSession().createQuery("from PaymentEntity")
                .list();
        return listOfPayment.stream()
                .map(PaymentConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void saveContPayment(Payment payment) {
        PaymentEntity paymentEntity = PaymentConverter.toEntity(payment);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(paymentEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletePayment(Long number) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete PaymentEntity au where au.number = :number")
                .setParameter("number", number)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

        @Override
        public void updApprComm (Long number, String approval, String comment) {
            final Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.createQuery("update PaymentEntity set approval=:approval, comment=:comment where number = :number")
                    .setParameter("approval", approval)
                    .setParameter("comment", comment)
                    .setParameter("number", number)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
