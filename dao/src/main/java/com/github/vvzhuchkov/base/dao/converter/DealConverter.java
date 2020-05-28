package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.DealEntity;
import com.github.vvzhuchkov.base.model.Deal;
import com.github.vvzhuchkov.base.model.Payment;

public class DealConverter {
    public static Deal fromEntity(DealEntity deal) {
        if (deal == null) {
            return null;
        }
        return new Deal(
                deal.getNumber(),
                deal.getLogin(),
                deal.getId(),
                deal.getPickup(),
                deal.getDropoff(),
                deal.getTotal(),
                deal.getApproval(),
                deal.getComment(),
                ContactConverter.fromEntity(deal.getContact()));
    }

    public static DealEntity toEntity(Payment deal) {
        if (deal == null) {
            return null;
        }
        final DealEntity dealEntity = new DealEntity();
        dealEntity.setNumber(deal.getNumber());
        dealEntity.setLogin(deal.getLogin());
        dealEntity.setId(deal.getId());
        dealEntity.setPickup(deal.getPickup());
        dealEntity.setDropoff(deal.getDropoff());
        dealEntity.setTotal(deal.getTotal());
        dealEntity.setApproval(deal.getApproval());
        dealEntity.setComment(deal.getComment());
        dealEntity.setContact(ContactConverter.toEntity(deal.getContact()));
        return dealEntity;
    }
}
