package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.dao.converter.BookingConverter;
import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.DealService;
import com.github.vvzhuchkov.base.service.BookingService;
import com.github.vvzhuchkov.base.service.PaymentService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultDealService;
import com.github.vvzhuchkov.base.service.impl.DefaultBookingService;
import com.github.vvzhuchkov.base.service.impl.DefaultPaymentService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String passport = request.getParameter("passport");
        if (name != null && surname != null && passport != null) {
            List<Booking> listOfBookingsByLogin = bookingService.getAllBookingsByLogin(authUser.getLogin(), 1).getList().stream().map(BookingConverter::fromEntity).collect(Collectors.toList());
            for (Booking booking : listOfBookingsByLogin) {
                if (paymentService.getPaymentByNumber(booking.getNumber()) == null) {
                    Long price = booking.getDays() * booking.getCar().getPrice();
                    Payment payment = new Payment(booking.getNumber(), authUser.getLogin(), surname.toUpperCase(), name.toUpperCase(), passport, booking.getId(), booking.getPickup(), booking.getDropoff(), price, "-", "Waiting for approval...");
                    paymentService.saveContPayment(payment);
                    bookingService.deleteBooking(payment.getNumber());
                }
            }
        }
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

        if (decline != null) {
            String number = request.getParameter("decline");
            String approval = "Rejected!";
            String comment = request.getParameter("commentDecline");
            paymentService.updApprComm(Long.parseLong(number), approval, comment);
        }
        if (accept != null) {
            String number = request.getParameter("accept");
            String approval = "Approved!";
            String comment = request.getParameter("commentAccept");
            paymentService.updApprComm(Long.parseLong(number), approval, comment);
        }
        if (delNumber != null) {
            paymentService.deleteBooking(Long.parseLong(delNumber));
        }
        if (payNumber != null) {
            dealService.saveAllApprovedPayments(Long.parseLong(payNumber));
        }
        WebUtils.redirect("/payment", request, response);
    }
}