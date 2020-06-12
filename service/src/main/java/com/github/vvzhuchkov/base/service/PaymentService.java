package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.ApprComm;
import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface PaymentService {

    Payment saveContPayment(Payment payment);

   List<Payment> getPaymentsByLogin (String login);

    ApprComm updApprComm(Long number, String approval, String comment);

    Payment getPaymentByNumber (Long number);

    List<Payment> getAllPaymentsForApproval();

    boolean deletePayment(Long delNumber);

}
