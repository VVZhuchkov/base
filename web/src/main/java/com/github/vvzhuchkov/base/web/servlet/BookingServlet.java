package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.dao.PaginationResult;
import com.github.vvzhuchkov.base.dao.converter.BookingConverter;
import com.github.vvzhuchkov.base.dao.entity.BookingEntity;
import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Booking;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.service.BookingService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultBookingService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/order")
public class BookingServlet extends HttpServlet {
    private BookingService bookingService = DefaultBookingService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        int page=0;
        try{
        page = Integer.parseInt(request.getParameter("page"));}
        catch (Exception e){
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
        if (listOfBookingsByLogin.size() == 0) {
            request.setAttribute("orderError", "You haven't done any order yet!");
        }
        String delNumber = request.getParameter("delNumber");
        if (delNumber == null) {
            WebUtils.forward("order", request, response);
            return;
        } else {
            bookingService.deleteBooking(Long.parseLong(delNumber));
            WebUtils.redirect("/order", request, response);
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
        Booking booking = new Booking(authUser.getLogin(), Long.parseLong(id), mainReq.getPickup(), mainReq.getDropoff(), days);
        bookingService.saveBooking(booking);
        WebUtils.redirect("/order", request, response);
    }
}
