package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if (role.equals("admin")) {
            WebUtils.redirect("/car", request, response);
            return;
        }
        WebUtils.redirect("/request", request, response);
    }
}
