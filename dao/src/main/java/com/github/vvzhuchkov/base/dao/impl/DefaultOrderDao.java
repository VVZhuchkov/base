package com.github.vvzhuchkov.base.dao.impl;

import com.github.vvzhuchkov.base.dao.HibernateUtil;
import com.github.vvzhuchkov.base.dao.OrderDao;
import com.github.vvzhuchkov.base.dao.converter.OrderConverter;
import com.github.vvzhuchkov.base.dao.entity.OrderEntity;
import com.github.vvzhuchkov.base.model.Order;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultOrderDao implements OrderDao {
    private static final Logger logOut = LoggerFactory.getLogger(DefaultOrderDao.class);
    public static volatile OrderDao instance;

    public static OrderDao getInstance() {
        OrderDao localInstance = instance;
        if (localInstance == null) {
            synchronized (OrderDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultOrderDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveOrder(Order order){
        OrderEntity orderEntity = OrderConverter.toEntity(order);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(orderEntity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOrder(Long number) {
       HibernateUtil.getSession().createQuery("delete from OrderEntity au where au.number = :number")
                    .setParameter("number", number)
                    .getSingleResult();
    }

    @Override
    public List<Order> getAllOrders(){
        final List<OrderEntity> listOfOrders = HibernateUtil.getSession().createQuery("from OrderEntity")
                .list();
        return listOfOrders.stream()
                .map(OrderConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrderByLogin(String login) {
        final List<OrderEntity> listOfOrdersByLogin = HibernateUtil.getSession().createQuery("select OrderEntity, CarEntity from OrderEntity oe where oe.login=:login")
                .list();
        return listOfOrdersByLogin.stream()
                .map(OrderConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderByNumber(Long number) {
        OrderEntity order;
        try {
            order = (OrderEntity) HibernateUtil.getSession().createQuery("from OrderEntity oe where oe.number = :number")
                    .setParameter("number", number)
                    .getSingleResult();
        } catch (NoResultException e) {
            logOut.info("Order not found by number {}", number);
            order = null;
        }
        return OrderConverter.fromEntity(order);
    }
}