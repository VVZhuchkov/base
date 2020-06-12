package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.ApprComm;
import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface PaymentDao {

    List<Payment> getPaymentsByLogin(String login);

    List<Payment> getAllPayments();

    Payment getPaymentByNumber(Long number);

    Payment saveContPayment(Payment payment);

    boolean deletePayment(Long delNumber);

    ApprComm updApprComm(Long number, String approval, String comment);
}
