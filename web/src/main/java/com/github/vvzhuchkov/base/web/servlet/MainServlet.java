package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        request.setAttribute("timeNow", dtf.format(timeNow));
        WebUtils.forward("main", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String location = request.getParameter("location");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        request.setAttribute("timeNow", dtf.format(timeNow));
        LocalDate pickup = Date.valueOf(request.getParameter("pickup")).toLocalDate();
        LocalDate dropoff = Date.valueOf(request.getParameter("dropoff")).toLocalDate();
        if (pickup.isAfter(dropoff)) {
            request.setAttribute("datesError", "Incorrect dates!");
            WebUtils.forward("main", request, response);
        }
        Request mainReq = new Request(authUser.getLogin(), location, pickup, dropoff);
        request.getSession().setAttribute("mainReq", mainReq);
        WebUtils.redirect("/city", request, response);
    }
}
