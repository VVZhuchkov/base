package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.*;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.ContactService;
import com.github.vvzhuchkov.base.service.DealService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.service.impl.DefaultContactService;
import com.github.vvzhuchkov.base.service.impl.DefaultDealService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();
    private ContactService contactService = DefaultContactService.getInstance();
    private DealService dealService = DefaultDealService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        List<Deal> dealList = dealService.getDealsByLogin(authUser.getLogin());
        List<Contact> listOfHistory = contactService.getAllContacts();
        for (Contact contact : listOfHistory) {
            if (contact.getLogin().equals(authUser.getLogin())) {
                contact.getDealList().addAll(dealList);
            }
        }
        request.setAttribute("histories", listOfHistory);
        if (dealList.size() == 0) {
            request.setAttribute("historyError", "You haven't rented any car yet!");
        }
        request.setAttribute("deal", dealList);
        WebUtils.forward("history", request, response);
    }
}