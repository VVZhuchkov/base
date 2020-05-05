package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Payment;

import java.util.List;

public interface PaymentService {

    void saveContPayment(Payment payment);

   List<Payment> getPaymentsByLogin (String login);

    void deleteOrder(Long delNumber);

    void updateComment(String newComment);

    void updateApproval (String newApp);

    Payment getPaymentByNumber (Long number);

    List<Payment> getAllPaymentsForApproval();

}
