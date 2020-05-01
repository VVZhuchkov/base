package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.forward("payment", request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.forward("/payment", request, response);
    }
}