package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface DealService {
    List<Deal> getAllDeals();

    Deal saveAllApprovedPayments(Long payNumber);

    List<Deal> getDealsByLogin (String login);
}
