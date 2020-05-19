package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.PaymentEntity;
import com.github.vvzhuchkov.base.model.Payment;

public class PaymentConverter {

    public static Payment fromEntity(PaymentEntity payment) {
        if (payment == null) {
            return null;
        }
        return new Payment(
                payment.getNumber(),
                payment.getLogin(),
                payment.getSurname(),
                payment.getName(),
                payment.getPassport(),
                payment.getId(),
                payment.getPickup(),
                payment.getDropoff(),
                payment.getTotal(),
                payment.getApproval(),
                payment.getComment());
    }

    public static PaymentEntity toEntity(Payment payment) {
        if (payment == null) {
            return null;
        }
        final PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setNumber(payment.getNumber());
        paymentEntity.setLogin(payment.getLogin());
        paymentEntity.setSurname(payment.getSurname());
        paymentEntity.setName(payment.getName());
        paymentEntity.setPassport(payment.getPassport());
        paymentEntity.setId(payment.getId());
        paymentEntity.setPickup(payment.getPickup());
        paymentEntity.setDropoff(payment.getDropoff());
        paymentEntity.setTotal(payment.getTotal());
        paymentEntity.setApproval(payment.getApproval());
        paymentEntity.setComment(payment.getComment());
        return paymentEntity;
    }
}
