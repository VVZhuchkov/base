package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.CarDao;
import com.github.vvzhuchkov.base.dao.OrderDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultCarDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultOrderDao;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Order;
import com.github.vvzhuchkov.base.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderService implements OrderService{

    private OrderDao orderDao = DefaultOrderDao.getInstance();
    private CarDao carDao = DefaultCarDao.getInstance();

    private static volatile OrderService instance;

    public static OrderService getInstance() {
        OrderService localInstance = instance;
        if (localInstance == null) {
            synchronized (OrderService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultOrderService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveOrder(String login, Car car) {
        Order order = orderDao.getOrderById(car.getId());
        if ((order == null) || (order != null && !order.getLogin().equals(login))) {
            orderDao.saveOrder(login, car);
        }
    }

            @Override
            public List<Car> getAllOrdersByLogin (String login){
                List<Order> listOfOrders = orderDao.getAllOrders();
                List<Car> listOfOrderedCarsByLogin = new ArrayList<>();
                for (Order order : listOfOrders) {
                    if (order.getLogin().equals(login)) {
                        listOfOrderedCarsByLogin.add(carDao.getById(order.getId()));
                    }
                }
                return listOfOrderedCarsByLogin;
            }
        }
