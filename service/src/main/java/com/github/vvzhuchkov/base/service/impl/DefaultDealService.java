package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.dao.OrderDao;
import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultDealDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultOrderDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultPaymentDao;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.DealService;

import java.util.List;

public class DefaultDealService implements DealService {
    private DealDao dealDao = DefaultDealDao.getInstance();
    private PaymentDao paymentDao = DefaultPaymentDao.getInstance();
    private OrderDao orderDao = DefaultOrderDao.getInstance();
    private static volatile DealService instance;

    public static DealService getInstance() {
        DealService localInstance = instance;
        if (localInstance == null) {
            synchronized (DealService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultDealService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Payment> getAllDeals() {
        return dealDao.getAllDeals();
    }

    @Override
    public void saveAllApprovedPayments(Long payNumber) {
        Payment payment = paymentDao.getPaymentByNumber(payNumber);
            dealDao.saveDeal(payment);
            paymentDao.deleteOrder(payment.getNumber());
    }

    @Override
    public List<Payment> getDealsByLogin (String login){
        return dealDao.getDealsByLogin(login);
    }
}
