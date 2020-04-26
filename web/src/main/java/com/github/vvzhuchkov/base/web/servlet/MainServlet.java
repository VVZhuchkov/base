package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.service.SecurityService;
import com.github.vvzhuchkov.base.service.impl.DefaultSecurityService;
import com.github.vvzhuchkov.base.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private SecurityService securityService = DefaultSecurityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    WebUtils.forward("main", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        }
    }
