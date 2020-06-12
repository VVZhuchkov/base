package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.PaymentDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultPaymentDaoTest {
    PaymentDao paymentDao = DefaultPaymentDao.getInstance();

    @Test
    void getInstance() {
        PaymentDao paymentDao = DefaultPaymentDao.getInstance();
        PaymentDao paymentDao2 = DefaultPaymentDao.getInstance();
        assertEquals(paymentDao, paymentDao2);
    }
}
