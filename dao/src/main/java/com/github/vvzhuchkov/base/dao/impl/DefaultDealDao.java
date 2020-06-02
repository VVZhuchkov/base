package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.converter.DealConverter;
import com.github.vvzhuchkov.base.dao.entity.DealEntity;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public void saveDeal(Deal deal) {
        DealEntity dealEntity = DealConverter.toEntity(deal);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(dealEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Deal> getDealsByLogin(String login) {
        final Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<DealEntity> criteria = cb.createQuery(DealEntity.class);
        Root<DealEntity> entityRoot = criteria.from(DealEntity.class);
        Predicate predicate = cb.and(
                cb.equal(entityRoot.get("login"), login));
        criteria.select(entityRoot).where(predicate);

        List<DealEntity> dealEntities = session.createQuery(criteria).getResultList();
        return dealEntities.stream()
                .map(DealConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Deal> getAllDeals() {
        final List<DealEntity> car = HibernateUtil.getSession().createQuery("from DealEntity")
                .list();
        return car.stream()
                .map(DealConverter::fromEntity)
                .collect(Collectors.toList());
    }
}