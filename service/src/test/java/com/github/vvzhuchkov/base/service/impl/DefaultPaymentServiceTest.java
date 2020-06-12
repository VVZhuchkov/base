package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.model.ApprComm;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.PaymentService;
import com.github.vvzhuchkov.base.service.RestitutionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultPaymentServiceTest {

    @Test
    void getInstance() {
        PaymentService paymentService = DefaultPaymentService.getInstance();
        PaymentService paymentService2 = DefaultPaymentService.getInstance();
        assertEquals(paymentService, paymentService2);
    }

    @Mock
    PaymentDao paymentDao;

    @InjectMocks
    DefaultPaymentService paymentService;

    @Test
    void getPaymentsByLogin() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Payment payment = new Payment(null, "login", 1L, pickup, dropoff, 35L, "-", "Waiting for approval...");
        when(paymentDao.getPaymentsByLogin("login")).thenReturn(Collections.singletonList(payment));
        List<Payment> savedPayment = paymentService.getPaymentsByLogin("login");
        assertEquals(savedPayment.get(0).getLogin(), "login");
        assertEquals(savedPayment.get(0).getPickup(), pickup);
    }

    @Test
    void deletePayment() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Payment payment = new Payment(null, "login", 1L, pickup, dropoff, 35L, "-", "Waiting for approval...");
        when(paymentDao.deletePayment(payment.getNumber())).thenReturn(true);
        final boolean delete = paymentService.deletePayment(payment.getNumber());
        assertTrue(delete);
    }

    @Test
    void updApprComm() {
        when(paymentDao.updApprComm(1L, "approved", "ok")).thenReturn(new ApprComm(1L, "approved", "ok"));
        ApprComm apprComm = paymentService.updApprComm(1L, "approved", "ok");
        assertEquals(apprComm.getApproval(), "approved");
        assertEquals(apprComm.getComment(), "ok");
    }

    @Test
    void getPaymentByNumber() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Payment payment = new Payment(null, "login", 1L, pickup, dropoff, 35L, "-", "Waiting for approval...");
        when(paymentDao.getPaymentByNumber(payment.getNumber())).thenReturn(payment);
        Payment savedPayment = paymentService.getPaymentByNumber(payment.getNumber());
        assertEquals(savedPayment.getLogin(), "login");
        assertEquals(savedPayment.getPickup(), pickup);
    }

    @Test
    void saveContPayment() {
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Payment payment = new Payment(null, "login", 1L, pickup, dropoff, 35L, "-", "Waiting for approval...");
        when(paymentDao.getPaymentsByLogin(payment.getLogin())).thenReturn(Collections.singletonList(payment));
        Payment savedPayment = paymentService.saveContPayment(payment);
        assertEquals(savedPayment.getTotal(), payment.getTotal());
    }

    @Test
    void getAllPaymentsForApproval(){
        LocalDate pickup = LocalDate.parse("2020-10-10");
        LocalDate dropoff = LocalDate.parse("2020-10-10");
        Payment payment = new Payment(null, "login", 1L, pickup, dropoff, 35L, "-", "Waiting for approval...");
        when(paymentDao.getAllPayments()).thenReturn(Collections.singletonList(payment));
        List<Payment> savedPaymentList = paymentService.getAllPaymentsForApproval();
        for(Payment savedPayment : savedPaymentList){
            assertEquals(savedPayment.getLogin(), payment.getLogin());
            assertEquals(savedPayment.getId(), payment.getId());
            assertEquals(savedPayment.getComment(), payment.getComment());
            assertEquals(savedPayment.getTotal(), payment.getTotal());
        }
    }
}
