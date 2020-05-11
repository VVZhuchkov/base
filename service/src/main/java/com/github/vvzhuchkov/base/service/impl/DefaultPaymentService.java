package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.dao.RoleUserDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultPaymentDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultRoleUserDao;
import com.github.vvzhuchkov.base.model.ApprComm;
import com.github.vvzhuchkov.base.model.Order;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.PaymentService;

import java.util.ArrayList;
import java.util.List;

public class DefaultPaymentService implements PaymentService {
    private PaymentDao paymentDao = DefaultPaymentDao.getInstance();
    private RoleUserDao roleUserDao = DefaultRoleUserDao.getInstance();

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
        List<Payment> listOfPaymentsByLogin = paymentDao.getPaymentsByLogin(payment.getLogin());
        List<Long> listOfIdsByLogin = new ArrayList<>();
        boolean isFlag = true;
        for (Payment existPayment : listOfPaymentsByLogin) {
            listOfIdsByLogin.add(existPayment.getId());
        }
        if (listOfIdsByLogin.contains(payment.getId())) {
            for (Payment existPayment : listOfPaymentsByLogin) {
                if ((existPayment.getId() == payment.getId()) && (!(payment.getDropoff().isBefore(existPayment.getPickup()) ||
                        payment.getPickup().isAfter(existPayment.getDropoff())))) {
                    isFlag = false;
                    break;
                }
            }
        }
        if (isFlag) {
            paymentDao.saveContPayment(payment);
        }
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
    public void updApprComm(Long number, String approval, String comment){
        ApprComm apprComm  = new ApprComm(number, approval, comment);
        paymentDao.updApprComm(apprComm);
   }

    @Override
    public Payment getPaymentByNumber(Long number){
        return paymentDao.getPaymentByNumber(number);
    }

    public List<Payment> getAllPaymentsForApproval(){
        String wait = "Waiting for approval...";
        String nope = "-";
        List<Payment> paymentList = paymentDao.getAllPayments();
        List<Payment> listForApproval = new ArrayList<>();
        for(Payment payment : paymentList){
            if(payment.getComment().equals(wait)){
                listForApproval.add(payment);
                }
            if((!payment.getApproval().equals(nope))&&roleUserDao.getRoleUserByLogin(payment.getLogin()).equals("admin")){
                listForApproval.add(payment);
            }
        }
        return listForApproval;
    }
}
