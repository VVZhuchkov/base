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
                deal.getSurname(),
                deal.getName(),
                deal.getPassport(),
                deal.getId(),
                deal.getPickup(),
                deal.getDropoff(),
                deal.getTotal(),
                deal.getApproval(),
                deal.getComment());
    }

    public static DealEntity toEntity(Payment deal) {
        if (deal == null) {
            return null;
        }
        final DealEntity dealEntity = new DealEntity();
        dealEntity.setNumber(deal.getNumber());
        dealEntity.setLogin(deal.getLogin());
        dealEntity.setSurname(deal.getSurname());
        dealEntity.setName(deal.getName());
        dealEntity.setPassport(deal.getPassport());
        dealEntity.setId(deal.getId());
        dealEntity.setPickup(deal.getPickup());
        dealEntity.setDropoff(deal.getDropoff());
        dealEntity.setTotal(deal.getTotal());
        dealEntity.setApproval(deal.getApproval());
        dealEntity.setComment(deal.getComment());

        return dealEntity;
    }
}
