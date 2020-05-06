package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.ApprComm;
import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface PaymentDao {

    List<Payment> getPaymentsByLogin(String login);

    List<Payment> getAllPayments();

    Payment getPaymentByNumber(Long number);

    void saveContPayment(Payment payment);

    void deleteOrder(Long delNumber);

    void updApprComm(ApprComm apprComm);
}
