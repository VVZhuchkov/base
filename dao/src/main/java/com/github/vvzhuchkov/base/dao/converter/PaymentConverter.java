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
                payment.getId(),
                payment.getPickup(),
                payment.getDropoff(),
                payment.getTotal(),
                payment.getApproval(),
                payment.getComment(),
                ContactConverter.fromEntity(payment.getContact()));
    }

    public static PaymentEntity toEntity(Payment payment) {
        if (payment == null) {
            return null;
        }
        final PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setNumber(payment.getNumber());
        paymentEntity.setLogin(payment.getLogin());
        paymentEntity.setId(payment.getId());
        paymentEntity.setPickup(payment.getPickup());
        paymentEntity.setDropoff(payment.getDropoff());
        paymentEntity.setTotal(payment.getTotal());
        paymentEntity.setApproval(payment.getApproval());
        paymentEntity.setComment(payment.getComment());
        paymentEntity.setContact(ContactConverter.toEntity(payment.getContact()));
        return paymentEntity;
    }
}
