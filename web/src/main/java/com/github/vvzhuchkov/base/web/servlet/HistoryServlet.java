package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.service.PaymentService;
import com.github.vvzhuchkov.base.service.impl.DefaultPaymentService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    private PaymentService paymentService = DefaultPaymentService.getInstance();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) {
            String accept = request.getParameter("accept");
         String decline = request.getParameter("decline");
         String declineComment = request.getParameter("declineComment");
            System.out.println(Long.parseLong(decline));
            System.out.println(declineComment);
            WebUtils.forward("history", request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) {
            WebUtils.forward("/history", request, response);
        }
    }

