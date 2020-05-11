package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface DealDao {

    void saveDeal(Payment payment);

    List<Payment> getDealsByLogin(String login);

    List<Payment> getAllDeals();
}
