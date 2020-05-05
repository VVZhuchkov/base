package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    void deleteOrder(Long delNumber);

    List<Order> getAllOrdersByLogin(String login);

}
