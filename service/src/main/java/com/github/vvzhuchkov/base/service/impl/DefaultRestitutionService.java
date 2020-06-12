package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.RestitutionDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultRestitutionDao;
import com.github.vvzhuchkov.base.model.Restitution;
import com.github.vvzhuchkov.base.service.RestitutionService;

import java.util.ArrayList;
import java.util.List;


public class DefaultRestitutionService implements RestitutionService {
    private RestitutionDao restitutionDao = DefaultRestitutionDao.getInstance();
    private static volatile RestitutionService instance;

    public static RestitutionService getInstance() {
        RestitutionService localInstance = instance;
        if (localInstance == null) {
            synchronized (RestitutionService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultRestitutionService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Restitution saveReturn(Restitution restitution) {
        return restitutionDao.saveReturn(restitution);
    }

    @Override
    public Restitution getRestitutionByNumber(Long number) {
        Restitution restitution = restitutionDao.getRestitutionsByNumber(number);
        return restitution;
    }

    @Override
    public List<Restitution> getRestitutionsByLogin(String login) {
        return restitutionDao.getRestitutionsByLogin(login);
    }

    @Override
    public List<Restitution> getAllRestitutions() {
        List<Restitution> listOfRestitutions = restitutionDao.getAllRestitutions();
        return listOfRestitutions;
    }

    @Override
    public boolean updStatus(Long number, String status) {
        restitutionDao.updStatus(number, status);
        return true;
    }

    @Override
    public boolean updStaComm(Long number, String status, String comment) {
        restitutionDao.updStaComm(number, status, comment);
        return true;
    }
}
