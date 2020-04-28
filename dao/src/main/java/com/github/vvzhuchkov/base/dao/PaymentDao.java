package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Payment;

public interface PaymentDao {

    //void savePayment(String login, Long id);

    Payment getPaymentByLogin(String login);
}
