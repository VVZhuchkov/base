package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Order;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.service.OrderService;
import com.github.vvzhuchkov.base.service.impl.DefaultOrderService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = DefaultOrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        List<Order> listOfOrdersByLogin = orderService.getAllOrdersByLogin(authUser.getLogin());
        request.setAttribute("orders", listOfOrdersByLogin);
        if (listOfOrdersByLogin.size() == 0) {
            request.setAttribute("orderError", "You haven't done any order yet!");
        }
        String delNumber = request.getParameter("delNumber");
        if (delNumber==null){
        WebUtils.forward("order", request, response);}
        else{
            orderService.deleteOrder(Long.parseLong(delNumber));
            WebUtils.redirect("/order", request, response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        Request mainReq = (Request) request.getSession().getAttribute("mainReq");
        Order order = new Order(authUser.getLogin(), Long.parseLong(id), mainReq.getPickup(), mainReq.getDropoff());
        orderService.saveOrder(order);
        WebUtils.redirect("/order", request, response);
    }
}
