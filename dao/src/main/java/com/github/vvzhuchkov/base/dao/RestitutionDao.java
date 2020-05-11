package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Restitution;

import java.util.List;

public interface RestitutionDao {

    void saveReturn(Restitution restitution);

    Restitution getRestitutionsByNumber(Long number);

    List<Restitution> getRestitutionsByLogin(String login);

    List<Restitution> getAllRestitutions();

    void updStatus(Long number, String status);

    void updStaComm(Long number, String status, String comment);
}
