package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.service.OrderService;
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
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser)request.getSession().getAttribute("authUser");
        List<Car> listOfOrdersByLogin = orderService.getAllOrdersByLogin(authUser.getLogin());
        request.setAttribute("orders", listOfOrdersByLogin);
        if (listOfOrdersByLogin.size() == 0) {
            request.setAttribute("orderError", "You haven't done any order yet!");
            WebUtils.forward("order", request, response);
        }
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
        if(id!=null) {
        orderService.saveOrder(authUser.getLogin(), Long.parseLong(id));}
        String delId = request.getParameter("delId");
        if(delId!=null){
            orderService.deleteOrder(Long.parseLong(delId));
        }
        WebUtils.redirect("/order", request, response);
    }
}
