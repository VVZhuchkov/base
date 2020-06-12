package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.ContactDao;
import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.dao.BookingDao;
import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultContactDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultDealDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultBookingDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultPaymentDao;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.DealService;

import java.util.ArrayList;
import java.util.List;

public class DefaultDealService implements DealService {
    private DealDao dealDao = DefaultDealDao.getInstance();
    private PaymentDao paymentDao = DefaultPaymentDao.getInstance();
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
    public List<Deal> getAllDeals() {
        return dealDao.getAllDeals();
    }

    @Override
    public Deal saveAllApprovedPayments(Long payNumber) {
        Payment payment = paymentDao.getPaymentByNumber(payNumber);
        Deal deal = new Deal(payNumber, payment.getLogin(), payment.getId(), payment.getPickup(), payment.getDropoff(),
                payment.getTotal(), payment.getApproval(), payment.getComment(), payment.getContact(), new ArrayList<>());
        dealDao.saveDeal(deal);
        paymentDao.deletePayment(payment.getNumber());
        return deal;
    }

    @Override
    public List<Deal> getDealsByLogin (String login){
        return dealDao.getDealsByLogin(login);
    }
}
