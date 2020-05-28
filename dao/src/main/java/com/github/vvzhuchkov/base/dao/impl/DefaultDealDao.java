package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.converter.DealConverter;
import com.github.vvzhuchkov.base.dao.entity.DealEntity;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;
import org.hibernate.Session;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultDealDao implements DealDao {
    public static volatile DealDao instance;

    public static DealDao getInstance() {
        DealDao localInstance = instance;
        if (localInstance == null) {
            synchronized (DealDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultDealDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveDeal(Payment payment) {
        DealEntity dealEntity = DealConverter.toEntity(payment);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(dealEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Deal> getDealsByLogin(String login){
        final List<DealEntity> listOfDealsByLogin = HibernateUtil.getSession().
                createQuery("from DealEntity de where de.login=:login order by de.number desc").
                setParameter("login", login)
                .list();
        return listOfDealsByLogin.stream()
                .map(DealConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Deal> getAllDeals(){
        final List<DealEntity> car = HibernateUtil.getSession().createQuery("from DealEntity")
                .list();
        return car.stream()
                .map(DealConverter::fromEntity)
                .collect(Collectors.toList());
    }
}