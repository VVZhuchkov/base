package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Order;

import java.util.List;

public interface OrderDao {

    void saveOrder(Order order);

    void deleteOrder(Long delNumber);

    List<Order> getAllOrders();

    List<Order> getOrderByLogin(String login);

    Order getOrderByNumber(Long number);
}
