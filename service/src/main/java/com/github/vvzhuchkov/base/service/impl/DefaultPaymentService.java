package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultPaymentDao;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.PaymentService;

import java.util.ArrayList;
import java.util.List;

public class DefaultPaymentService implements PaymentService {
    private PaymentDao paymentDao = DefaultPaymentDao.getInstance();

    private static volatile PaymentService instance;

    public static PaymentService getInstance() {
        PaymentService localInstance = instance;
        if (localInstance == null) {
            synchronized (PaymentService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultPaymentService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveContPayment(Payment payment) {
        paymentDao.saveContPayment(payment);
    }

    @Override
    public List<Payment> getPaymentsByLogin (String login){
        return paymentDao.getPaymentsByLogin(login);
    }

    @Override
    public void deleteOrder(Long delNumber){
        paymentDao.deleteOrder(delNumber);
    }

    @Override
   public void updateComment(String newComment){
        paymentDao.updateComment(newComment);
    }

   public void updateApproval (String newApp){
        paymentDao.updateApproval (newApp);
   }

    @Override
    public Payment getPaymentByNumber(Long number){
        return paymentDao.getPaymentByNumber(number);
    }

    public List<Payment> getAllPaymentsForApproval(){
        List<Payment> paymentList = paymentDao.getAllPayments();
        List<Payment> listForApproval = new ArrayList<>();
        for(Payment payment : paymentList){
            if(payment.getComment().equals("Waiting for approval...")){
                listForApproval.add(payment);
                }
        }
        return listForApproval;
    }
}
