package com.github.vvzhuchkov.base.service.impl;

import com.github.vvzhuchkov.base.dao.OrderDao;
import com.github.vvzhuchkov.base.dao.PaymentDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultOrderDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultPaymentDao;
import com.github.vvzhuchkov.base.model.Order;
import com.github.vvzhuchkov.base.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderService implements OrderService {

    private OrderDao orderDao = DefaultOrderDao.getInstance();

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
    public void saveOrder(Order order) {
        List<Order> listOrdersByLogin = orderDao.getOrderByLogin(order.getLogin());
        List<Long> listOfIdsByLogin = new ArrayList<>();
        boolean isFlag = true;
        for (Order existOrder : listOrdersByLogin) {
            listOfIdsByLogin.add(existOrder.getId());
        }
        if (listOfIdsByLogin.contains(order.getId())) {
            for (Order existOrder : listOrdersByLogin) {
                if ((existOrder.getId() == order.getId()) && (!(order.getDropoff().isBefore(existOrder.getPickup()) ||
                        order.getPickup().isAfter(existOrder.getDropoff())))) {
                    isFlag = false;
                    break;
                }
            }
        }
        if (isFlag) {
            orderDao.saveOrder(order);
        }
    }

    @Override
    public void deleteOrder(Long delNumber) {
        orderDao.deleteOrder(delNumber);
    }

    @Override
    public List<Order> getAllOrdersByLogin(String login) {
        return orderDao.getOrderByLogin(login);
    }

}
