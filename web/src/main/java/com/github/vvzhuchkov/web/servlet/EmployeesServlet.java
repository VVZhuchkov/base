package com.github.vvzhuchkov.web.servlet;

import com.github.vvzhuchkov.model.AuthUser;
import com.github.vvzhuchkov.service.SecurityService;
import com.github.vvzhuchkov.service.impl.DefaultSecurityService;
import com.github.vvzhuchkov.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee")
public class EmployeesServlet extends HttpServlet {
    private SecurityService securityService = DefaultSecurityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<AuthUser> employees = new ArrayList<>(securityService.getEmployees().values());
        request.setAttribute("employees", employees);
        WebUtils.forward("employee", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        }
    }
