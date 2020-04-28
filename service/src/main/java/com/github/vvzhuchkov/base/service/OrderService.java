package com.github.vvzhuchkov.base.service;

import com.github.vvzhuchkov.base.model.Car;

import java.util.List;

public interface OrderService {

    void saveOrder(String login, Long id);

    void deleteOrder(Long id);

    List<Car> getAllOrdersByLogin(String login);
}
