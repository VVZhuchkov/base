package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface PaymentService {

    void saveContPayment(Payment payment);

   List<Payment> getPaymentsByLogin (String login);

    void deleteBooking(Long delNumber);

    void updApprComm(Long number, String approval, String comment);

    Payment getPaymentByNumber (Long number);

    List<Payment> getAllPaymentsForApproval();

}
