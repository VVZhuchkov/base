package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.dao.OrderDao;
import com.github.vvzhuchkov.base.dao.impl.DefaultOrderDao;
import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Order;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.model.RoleUser;
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
            Payment payment = new Payment(order.getNumber(), authUser.getLogin(), surname.toUpperCase(), name.toUpperCase(), passport, order.getId(), order.getPickup(), order.getDropoff(), price, "-", "Confirm your order and wait for admin's approval in 5 min");
            paymentService.saveContPayment(payment);}}}
        List<Payment> listOfPaymentsByLogin = paymentService.getPaymentsByLogin(authUser.getLogin());
        request.setAttribute("payments", listOfPaymentsByLogin);
        List<Payment> paymentsForApprovalList = paymentService.getAllPaymentsForApproval();
        request.setAttribute("approvals", paymentsForApprovalList);
        if (listOfPaymentsByLogin.size() == 0) {
            request.setAttribute("paymentError", "You haven't confirmed any order yet!");
        }
        if (paymentsForApprovalList.size() == 0) {
            request.setAttribute("paymentAdError", "You don't have any new payments to confirm!");
        }
        String delNumber = request.getParameter("delNumber");
        if (delNumber==null){
            WebUtils.forward("payment", request, response);}
        else{
            paymentService.deleteOrder(Long.parseLong(delNumber));
            WebUtils.redirect("/payment", request, response);
        }
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
       List<Payment> paymentList = paymentService.getPaymentsByLogin(authUser.getLogin());
       String newComment="Waiting for approval...";
       String confirm = request.getParameter("confirm");
       if(confirm.equals("confirm")){
           paymentService.updateComment(newComment);
       }
       WebUtils.redirect("/payment", request, response);
    }
}