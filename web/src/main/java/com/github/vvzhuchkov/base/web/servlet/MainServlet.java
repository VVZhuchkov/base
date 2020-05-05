package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime timeTomorrow = timeNow.plusDays(1);
        request.setAttribute("timeNow", dtf.format(timeNow));
        request.setAttribute("timeTomorrow", dtf.format(timeTomorrow));
    WebUtils.forward("main", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser)request.getSession().getAttribute("authUser");
        String location = request.getParameter("location");
        LocalDate pickup= Date.valueOf(request.getParameter("pickup")).toLocalDate();
        LocalDate dropoff= Date.valueOf(request.getParameter("dropoff")).toLocalDate();
        Request mainReq = new Request(authUser.getLogin(), location, pickup, dropoff);
        request.getSession().setAttribute("mainReq", mainReq);
        WebUtils.redirect("/city",request,response);
        }
    }