package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Restitution;

import java.util.List;

public interface RestitutionDao {

    Restitution saveReturn(Restitution restitution);

    Restitution getRestitutionsByNumber(Long number);

    List<Restitution> getRestitutionsByLogin(String login);

    List<Restitution> getAllRestitutions();

    boolean updStatus(Long number, String status);

    boolean updStaComm(Long number, String status, String comment);

    void deleteRestitution(Long number);
}
