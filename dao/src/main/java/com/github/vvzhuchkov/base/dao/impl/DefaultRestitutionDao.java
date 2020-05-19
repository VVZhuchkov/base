package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.RestitutionDao;
import com.github.vvzhuchkov.base.dao.converter.RestitutionConverter;
import com.github.vvzhuchkov.base.dao.entity.RestitutionEntity;
import com.github.vvzhuchkov.base.model.Restitution;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultRestitutionDao implements RestitutionDao {
    private static final Logger logOut = LoggerFactory.getLogger(DefaultRestitutionDao.class);
    public static volatile RestitutionDao instance;

    public static RestitutionDao getInstance() {
        RestitutionDao localInstance = instance;
        if (localInstance == null) {
            synchronized (RestitutionDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRestitutionDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveReturn(Restitution restitution) {
        RestitutionEntity restitutionEntity = RestitutionConverter.toEntity(restitution);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(restitutionEntity);
        session.getTransaction().commit();
    }

    @Override
    public Restitution getRestitutionsByNumber(Long number) {
        RestitutionEntity restitution;
        try {
            restitution = (RestitutionEntity) HibernateUtil.getSession().createQuery("from RestitutionEntity re where re.number = :number")
                    .setParameter("number", number)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("Restitution not found by number {}", number);
            restitution = null;
        }
        return RestitutionConverter.fromEntity(restitution);
    }

    @Override
    public List<Restitution> getRestitutionsByLogin(String login) {
        final List<RestitutionEntity> listOfRestitutionsByLogin = HibernateUtil.getSession().createQuery("from RestitutionEntity re where re.login=:login")
                .list();
        return listOfRestitutionsByLogin.stream()
                .map(RestitutionConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restitution> getAllRestitutions() {
        final List<RestitutionEntity> listOfAllRestitutions = HibernateUtil.getSession().createQuery("from RestitutionEntity")
                .list();
        return listOfAllRestitutions.stream()
                .map(RestitutionConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void updStatus(Long number, String status) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update RestitutionEntity set status=:status where number = :number")
                .setParameter("status", status)
                .setParameter("number", number)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void updStaComm(Long number, String status, String comment) {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("update RestitutionEntity set status=:status, comment=:comment where number = :number")
                .setParameter("status", status)
                .setParameter("comment", comment)
                .setParameter("number", number)
                .executeUpdate();
        session.getTransaction().commit();
    }
}
