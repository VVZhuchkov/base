package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.RestitutionDao;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Restitution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultRestitutionDaoTest {
    RestitutionDao restitutionDao = DefaultRestitutionDao.getInstance();

    @Test
    void getInstance() {
        RestitutionDao restitutionDao = DefaultRestitutionDao.getInstance();
        RestitutionDao restitutionDao2 = DefaultRestitutionDao.getInstance();
        assertEquals(restitutionDao, restitutionDao2);
    }

    @Test
    void saveReturn() {
        final Restitution restitution = new Restitution(1L, "vzhuchkov", "OK", 1L, "available");
        final Restitution savedRestitution = restitutionDao.saveReturn(restitution);
        assertEquals(restitution.getLogin(), savedRestitution.getLogin());
        assertEquals(restitution.getComment(), savedRestitution.getComment());
        restitutionDao.deleteRestitution(savedRestitution.getNumber());
    }

    @Test
    void getRestitutionsByNumber() {
        final Restitution emptyRestitution = restitutionDao.getRestitutionsByNumber(2L);
        assertNull(emptyRestitution);
        final Restitution restitution = restitutionDao.saveReturn(new Restitution(
                1L, "vzhuchkov", "OK", 1L, "available"));
        Long number = restitution.getNumber();
        final Restitution savedRestitution = restitutionDao.getRestitutionsByNumber(number);
        assertEquals(restitution.getLogin(), savedRestitution.getLogin());
        assertEquals(restitution.getComment(), savedRestitution.getComment());
        restitutionDao.deleteRestitution(number);
    }

    @Test
    void getRestitutionsByLogin() {
        final Restitution restitution = new Restitution(1L, "vzhuchkov", "OK", 1L, "available");
        final List<Restitution> savedRestitutionList = restitutionDao.getRestitutionsByLogin(restitution.getLogin());
        for (Restitution savedRestitution : savedRestitutionList) {
            assertEquals(restitution.getLogin(), savedRestitution.getLogin());
            assertEquals(restitution.getComment(), savedRestitution.getComment());
            restitutionDao.deleteRestitution(savedRestitution.getNumber());
        }
    }

    @Test
    void getAllRestitutions() {
        final Restitution restitution = new Restitution(1L, "vzhuchkov", "OK", 1L, "available");
        final List<Restitution> savedRestitutionList = restitutionDao.getAllRestitutions();
        for (Restitution savedRestitution : savedRestitutionList) {
            assertEquals(restitution.getLogin(), savedRestitution.getLogin());
            assertEquals(restitution.getComment(), savedRestitution.getComment());
            restitutionDao.deleteRestitution(savedRestitution.getNumber());
        }
    }

    @Test
    void updStatus() {
        final Restitution restitution = restitutionDao.saveReturn(new Restitution(1L, "vzhuchkov", "OK", 1L, "available"));
        boolean isChangedStatus = restitutionDao.updStatus(1L, "not available");
        assertTrue(isChangedStatus);
        restitutionDao.deleteRestitution(restitution.getNumber());
    }

    @Test
    void updStaComm() {
        final Restitution restitution = restitutionDao.saveReturn(new Restitution(1L, "vzhuchkov", "OK", 1L, "available"));
        boolean isChangedStatus = restitutionDao.updStaComm(1L, "not available", "No");
        assertTrue(isChangedStatus);
        restitutionDao.deleteRestitution(restitution.getNumber());
    }
}
