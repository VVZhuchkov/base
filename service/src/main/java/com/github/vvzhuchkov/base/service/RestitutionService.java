package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Restitution;

import java.util.List;

public interface RestitutionService {

    Restitution saveReturn(Restitution restitution);

    Restitution getRestitutionByNumber(Long number);

    List<Restitution> getAllRestitutions();

    List<Restitution> getRestitutionsByLogin(String login);

    boolean updStatus(Long number, String status);

    boolean updStaComm(Long number, String status, String comment);
}
