package com.github.vvzhuchkov.base.dao.converter;

import com.github.vvzhuchkov.base.dao.entity.OrderEntity;
import com.github.vvzhuchkov.base.model.Order;

public class OrderConverter {
    public static Order fromEntity(OrderEntity order) {
        if (order == null) {
            return null;
        }
        return new Order(
                order.getNumber(),
                order.getLogin(),
                order.getId(),
                order.getPickup(),
                order.getDropoff(),
                order.getDays());
    }

    public static OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setNumber(order.getNumber());
        orderEntity.setLogin(order.getLogin());
        orderEntity.setId(order.getId());
        orderEntity.setPickup(order.getPickup());
        orderEntity.setDropoff(order.getDropoff());
        orderEntity.setDays(order.getDays());
        return orderEntity;
    }
}
