package com.github.vvzhuchkov.base.dao;

import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Order;

import java.util.List;

public interface OrderDao {

    void saveOrder(String login, Car car);

    List<Order> getAllOrders();

    Order getOrderById(Long id);
}
