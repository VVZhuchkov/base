package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.dao.impl.PaginationResult;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.*;
import com.github.vvzhuchkov.base.service.BookingService;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.PaymentService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultBookingService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.service.impl.DefaultPaymentService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@WebServlet("/order")
public class BookingServlet extends HttpServlet {
    private BookingService bookingService = DefaultBookingService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();
    private PaymentService paymentService = DefaultPaymentService.getInstance();
    private CarService carService = DefaultCarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        int page = 0;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        PaginationResult<BookingEntity> paginationResult = bookingService.getAllBookingsByLogin(authUser.getLogin(), page);
        List<BookingEntity> listOfBookingsByLogin = paginationResult.getList();
        int totalPages = paginationResult.getTotalPages();
        request.setAttribute("totalPages", totalPages);
        // 1 2 3 4 5 ... 11 12 13
        List<Integer> navigationPages = paginationResult.getNavigationPages();
        request.setAttribute("navigationPages", navigationPages);
        request.setAttribute("bookings", listOfBookingsByLogin);
        request.setAttribute("orderError", "You haven't done any order yet!");
        String delNumber = request.getParameter("delNumber");
        String bookingNumber = request.getParameter("bookingNumber");
        if(bookingNumber==null&&delNumber==null){
            WebUtils.forward("order", request, response);
            return;
        }
        else if (delNumber != null) {
            bookingService.deleteBooking(Long.parseLong(delNumber));
            WebUtils.redirect("/order", request, response);
            return;
        } else if (bookingNumber != null) {
            if (paymentService.getPaymentByNumber(Long.parseLong(bookingNumber)) == null) {
                Booking booking = bookingService.getBookingByNumber(Long.parseLong(bookingNumber));
                Long price = booking.getDays() * booking.getCar().getPrice();
                Payment payment = new Payment(booking.getNumber(), authUser.getLogin(), booking.getId(), booking.getPickup(), booking.getDropoff(), price, "-", "Waiting for approval...");
                paymentService.saveContPayment(payment);
                bookingService.deleteBooking(Long.parseLong(bookingNumber));
            }
            WebUtils.redirect("/payment", request, response);
            return;
        } else if (listOfBookingsByLogin.size() == 0) {
            WebUtils.forward("order", request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        Request mainReq = (Request) request.getSession().getAttribute("mainReq");
        LocalDate pickup = Date.valueOf(mainReq.getPickup()).toLocalDate();
        LocalDate dropoff = Date.valueOf(mainReq.getDropoff()).toLocalDate();
        long days = ChronoUnit.DAYS.between(pickup, dropoff) + 1L;
        Booking booking = new Booking(null, authUser.getLogin(), Long.parseLong(id), mainReq.getPickup(), mainReq.getDropoff(), days);
        bookingService.saveBooking(booking);
        WebUtils.redirect("/order", request, response);
    }
}
