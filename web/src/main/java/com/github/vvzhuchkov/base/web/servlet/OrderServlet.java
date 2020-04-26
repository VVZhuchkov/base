package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.OrderService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.service.impl.DefaultOrderService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private CarService carService = DefaultCarService.getInstance();
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser)request.getSession().getAttribute("authUser");
        List<Car> listOfOrdersByLogin = orderService.getAllOrdersByLogin(authUser.getLogin());
        request.setAttribute("orders", listOfOrdersByLogin);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeTomorrow = timeNow.plusDays(1);
        request.setAttribute("timeNow", dtf.format(timeNow));
        request.setAttribute("timeTomorrow", dtf.format(timeTomorrow));
        WebUtils.forward("order", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        AuthUser authUser = (AuthUser)request.getSession().getAttribute("authUser");
        Car car = carService.getById(Long.parseLong(id));
        orderService.saveOrder(authUser.getLogin(), car);
        WebUtils.redirect("/order", request, response);
    }
}
