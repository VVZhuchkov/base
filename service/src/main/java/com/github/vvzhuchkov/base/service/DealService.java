package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface DealService {
    List<Payment> getAllDeals();

    void saveAllApprovedPayments(Long payNumber);

    List<Payment> getDealsByLogin (String login);
}
