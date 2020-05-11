package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Payment;
import com.github.vvzhuchkov.base.service.DealService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultDealService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deal")
public class DealServlet extends HttpServlet {
    private DealService dealService = DefaultDealService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        List<Payment> listOfDeals = new ArrayList<>();
        if (role.equals("admin")) {
            listOfDeals = dealService.getAllDeals();}
            if (role.equals("user")) {
                listOfDeals = dealService.getDealsByLogin(authUser.getLogin());
            }
            if (listOfDeals.size() == 0) {
                request.setAttribute("dealError", "You haven't had any deals yet");
            }
            request.setAttribute("deals", listOfDeals);
            WebUtils.forward("deal", request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.forward("/deal", request, response);
    }
}

