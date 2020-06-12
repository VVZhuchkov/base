package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.RestitutionDao;
import com.github.vvzhuchkov.base.model.Restitution;
import com.github.vvzhuchkov.base.service.RestitutionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultRestitutionServiceTest {

    @Test
    void getInstance() {
        RestitutionService restitutionService = DefaultRestitutionService.getInstance();
        RestitutionService restitutionService2 = DefaultRestitutionService.getInstance();
        assertEquals(restitutionService, restitutionService2);
    }

    @Mock
    RestitutionDao restitutionDao;

    @InjectMocks
    DefaultRestitutionService restitutionService;

    @Test
    void saveReturn() {
        Restitution restitution = new Restitution(null, "login", "comment", 0L, "status");
        when(restitutionDao.saveReturn(restitution)).thenReturn(restitution);
        Restitution savedRestitution = restitutionService.saveReturn(restitution);
        assertEquals(savedRestitution.getLogin(), "login");
        assertEquals(savedRestitution.getComment(), "comment");
        assertEquals(savedRestitution.getRating(), (Long) 0L);
    }

    @Test
    void getRestitutionByNumber() {
        Restitution restitution = new Restitution(null, "login", "comment", 0L, "status");
        when(restitutionDao.getRestitutionsByNumber(restitution.getNumber())).thenReturn(restitution);
        Restitution savedRestitution = restitutionService.getRestitutionByNumber(restitution.getNumber());
        assertEquals(savedRestitution.getLogin(), "login");
        assertEquals(savedRestitution.getComment(), "comment");
        assertEquals(savedRestitution.getRating(), (Long) 0L);
    }

    @Test
    void getAllRestitutions() {
        Restitution restitution = new Restitution(null, "login", "comment", 0L, "status");
        when(restitutionDao.getAllRestitutions()).thenReturn(Collections.singletonList(restitution));
        List<Restitution> savedRestitutionList = restitutionService.getAllRestitutions();
        for (Restitution savedRestitution : savedRestitutionList) {
            assertEquals(savedRestitution.getLogin(), "login");
            assertEquals(savedRestitution.getComment(), "comment");
            assertEquals(savedRestitution.getRating(), (Long) 0L);
        }
    }

    @Test
    void getRestitutionsByLogin() {
        Restitution restitution = new Restitution(null, "login", "comment", 0L, "status");
        when(restitutionDao.getRestitutionsByLogin(restitution.getLogin())).thenReturn(Collections.singletonList(restitution));
        List<Restitution> savedRestitutionList = restitutionService.getRestitutionsByLogin(restitution.getLogin());
        for (Restitution savedRestitution : savedRestitutionList) {
            assertEquals(savedRestitution.getLogin(), "login");
            assertEquals(savedRestitution.getComment(), "comment");
            assertEquals(savedRestitution.getRating(), (Long) 0L);
        }
    }

    @Test
    void updStatus() {
        Restitution restitution = new Restitution(null, "login", "comment", 0L, "status");
        Long number = restitution.getNumber();
        when(restitutionDao.updStatus(number, "newStatus")).thenReturn(true);
        boolean isSavedRestitution = restitutionService.updStatus(number, "newStatus");
        assertTrue(isSavedRestitution);
    }

    @Test
    void updStaComm() {
        Restitution restitution = new Restitution(null, "login", "comment", 0L, "status");
        Long number = restitution.getNumber();
        when(restitutionDao.updStaComm(number, "newStatus", "newComment")).thenReturn(true);
        boolean isSavedRestitution = restitutionService.updStaComm(number, "newStatus", "newComment");
        assertTrue(isSavedRestitution);
    }
}
