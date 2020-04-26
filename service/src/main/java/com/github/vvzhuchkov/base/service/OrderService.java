package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(String login, Car car);

    List<Car> getAllOrdersByLogin(String login);

}
