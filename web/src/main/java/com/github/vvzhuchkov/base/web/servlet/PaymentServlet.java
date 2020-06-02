package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.dao.converter.BookingConverter;
import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.*;
import com.github.vvzhuchkov.base.service.impl.*;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private PaymentService paymentService = DefaultPaymentService.getInstance();
    private BookingService bookingService = DefaultBookingService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();
    private DealService dealService = DefaultDealService.getInstance();
    private ContactService contactService = DefaultContactService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        List<Payment> listOfAllPayments = new ArrayList<>();
        if (role.equals("admin")) {
            listOfAllPayments = paymentService.getAllPaymentsForApproval();
        }
        if (role.equals("user")) {
            listOfAllPayments = paymentService.getPaymentsByLogin(authUser.getLogin());
        }
        if (listOfAllPayments.size() == 0) {
            request.setAttribute("paymentError", "You haven't confirmed any order yet!");
        }
        request.setAttribute("payments", listOfAllPayments);
        WebUtils.forward("payment", request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        String accept = request.getParameter("accept");
        String decline = request.getParameter("decline");
        String delNumber = request.getParameter("delNumber");
        String payNumber = request.getParameter("payNumber");
        if (accept==null&&decline==null&&delNumber==null&&payNumber==null){
            WebUtils.forward("payment", request, response);
            return;
        }
        else if (decline != null) {
            String number = request.getParameter("decline");
            String approval = "Rejected";
            String comment = request.getParameter("commentDecline");
            paymentService.updApprComm(Long.parseLong(number), approval, comment);
            WebUtils.redirect("/payment", request, response);
            return;
        }
        else if (accept != null) {
            String number = request.getParameter("accept");
            String approval = "Approved";
            String comment = request.getParameter("commentAccept");
            paymentService.updApprComm(Long.parseLong(number), approval, comment);
            WebUtils.redirect("/payment", request, response);
            return;
        }
        else if (delNumber != null) {
            paymentService.deleteBooking(Long.parseLong(delNumber));
            WebUtils.redirect("/payment", request, response);
            return;
        }
        else if (payNumber != null) {
            dealService.saveAllApprovedPayments(Long.parseLong(payNumber));
            WebUtils.redirect("/deal", request, response);
        }
    }
}