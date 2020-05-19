package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface DealDao {

    void saveDeal(Payment payment);

    List<Deal> getDealsByLogin(String login);

    List<Deal> getAllDeals();
}
