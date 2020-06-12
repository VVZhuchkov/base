package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.DealDao;
import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultDealDao;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.DealService;
import com.github.vvzhuchkov.base.service.RestitutionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultDealServiceTest {

    @Test
    void getInstance() {
        DealService dealService = DefaultDealService.getInstance();
        DealService dealService2 = DefaultDealService.getInstance();
        assertEquals(dealService, dealService2);
    }

    @Mock
    DealDao dealDao;

    @Mock
    PaymentDao paymentDao;

    @InjectMocks
    DefaultDealService dealService;

    @Test
    void saveAllApprovedPayments() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Payment payment = new Payment(null, "login", 1L, pickup, dropoff, 35L, "-", "Waiting for approval...");
        Long paymentNumber = payment.getNumber();
        when(paymentDao.getPaymentByNumber(paymentNumber)).thenReturn(payment);
        Deal savedDeal = dealService.saveAllApprovedPayments(paymentNumber);
        assertEquals(savedDeal.getLogin(), payment.getLogin());
        assertEquals(savedDeal.getTotal(), payment.getTotal());
        assertEquals(savedDeal.getComment(), payment.getComment());
    }

    @Test
    void getDealsByLogin() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Deal deal = new Deal(null, "login", 1L, pickup, dropoff, 35L, "approved", "ok", null);
        when(dealDao.getDealsByLogin(deal.getLogin())).thenReturn(Collections.singletonList(deal));
        List<Deal> savedDealList = dealService.getDealsByLogin(deal.getLogin());
        for (Deal savedDeal : savedDealList) {
            assertEquals(savedDeal.getLogin(), deal.getLogin());
            assertEquals(savedDeal.getApproval(), deal.getApproval());
            assertEquals(savedDeal.getComment(), deal.getComment());
            assertEquals(savedDeal.getPickup(), deal.getPickup());
            assertEquals(savedDeal.getDropoff(), deal.getDropoff());
        }
    }

    @Test
    void getAllDeals() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Deal deal = new Deal(null, "login", 1L, pickup, dropoff, 35L, "approved", "ok", null);
        when(dealDao.getAllDeals()).thenReturn(Collections.singletonList(deal));
        List<Deal> savedDealList = dealService.getAllDeals();
        for (Deal savedDeal : savedDealList) {
            assertEquals(savedDeal.getLogin(), deal.getLogin());
            assertEquals(savedDeal.getTotal(), deal.getTotal());
            assertEquals(savedDeal.getComment(), deal.getComment());
        }
    }
}