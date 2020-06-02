package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.DealEntity;
import com.github.vvzhuchkov.base.model.Deal;

import java.util.stream.Collectors;

public class DealConverter {
    public static Deal fromEntity(DealEntity dealEntity) {
        if (dealEntity == null) {
            return null;
        }
        return new Deal(
                dealEntity.getNumber(),
                dealEntity.getLogin(),
                dealEntity.getId(),
                dealEntity.getPickup(),
                dealEntity.getDropoff(),
                dealEntity.getTotal(),
                dealEntity.getApproval(),
                dealEntity.getComment(),
                ContactConverter.fromEntity(dealEntity.getContact()),
                dealEntity.getContactEntityList().stream().map(ContactConverter::fromEntity).collect(Collectors.toList()));
    }

    public static DealEntity toEntity(Deal deal) {
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
        dealEntity.setContactEntityList(deal.getContactList().stream().map(ContactConverter::toEntity).collect(Collectors.toList()));
        return dealEntity;
    }
}
