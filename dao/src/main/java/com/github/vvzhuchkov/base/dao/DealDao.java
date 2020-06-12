package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface DealDao {

    Deal saveDeal(Deal deal);

    List<Deal> getDealsByLogin(String login);

    List<Deal> getAllDeals();

    boolean deleteDeal(Long number);
}
