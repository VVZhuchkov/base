package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Order;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.OrderService;
import com.github.vvzhuchkov.base.service.PaymentService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultOrderService;
import com.github.vvzhuchkov.base.service.impl.DefaultPaymentService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private PaymentService paymentService = DefaultPaymentService.getInstance();
    private OrderService orderService = DefaultOrderService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String passport = request.getParameter("passport");
        if (name!=null&&surname!=null&&passport!=null){
        List<Order> listOfOrdersByLogin = orderService.getAllOrdersByLogin(authUser.getLogin());
        for (Order order : listOfOrdersByLogin) {
            if (paymentService.getPaymentByNumber(order.getNumber())==null){
            Long price = order.getDays() * order.getPrice();
            Payment payment = new Payment(order.getNumber(), authUser.getLogin(), surname.toUpperCase(), name.toUpperCase(), passport, order.getId(), order.getPickup(), order.getDropoff(), price, "-", "Waiting for approval...");
            paymentService.saveContPayment(payment);}}}
        List<Payment> listOfAllPayments = new ArrayList<>();
        if(role.equals("admin")){
            listOfAllPayments = paymentService.getAllPaymentsForApproval();}
        if(role.equals("user")){
                listOfAllPayments = paymentService.getPaymentsByLogin(authUser.getLogin()); }
                if (listOfAllPayments.size() == 0) {
                request.setAttribute("paymentError", "You haven't confirmed any order yet!");
            }
            request.setAttribute("payments", listOfAllPayments);
        String delNumber = request.getParameter("delNumber");
        if (delNumber==null){
            WebUtils.forward("payment", request, response);}
        else{
            paymentService.deleteOrder(Long.parseLong(delNumber));
            WebUtils.redirect("/payment", request, response);
        }
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
       String accept = request.getParameter("accept");
       String decline = request.getParameter("decline");
       if (accept==null){
           String number = request.getParameter("decline");
           String approval = "Rejected!";
           String comment = request.getParameter("commentDecline");
           paymentService.updApprComm(Long.parseLong(number), approval, comment);
       }
       if(decline==null){
           String number = request.getParameter("accept");
           String approval = "Approved!";
           String comment = request.getParameter("commentAccept");
           paymentService.updApprComm(Long.parseLong(number), approval, comment );
       }
       WebUtils.redirect("/payment", request, response);
    }
}