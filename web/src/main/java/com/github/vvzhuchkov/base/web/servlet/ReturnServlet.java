package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.*;
import com.github.vvzhuchkov.base.service.DealService;
import com.github.vvzhuchkov.base.service.RestitutionService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultDealService;
import com.github.vvzhuchkov.base.service.impl.DefaultRestitutionService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/return")
public class ReturnServlet extends HttpServlet {
    private DealService dealService = DefaultDealService.getInstance();
    private RestitutionService restitutionService = DefaultRestitutionService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        String comment = "-";
        List<Deal> listOfDeals = dealService.getAllDeals();
        for (Deal deal : listOfDeals) {
            if (restitutionService.getRestitutionByNumber(deal.getNumber()) == null) {
                Restitution restitution = new Restitution(deal.getNumber(), deal.getLogin(), comment, roleUserService.getRatingUserByLogin(deal.getLogin()), "In operation");
                restitutionService.saveReturn(restitution);
            }}
        List<Restitution> listOfRestitutions = new ArrayList<>();
        if (role.equals("admin")) {
            listOfRestitutions = restitutionService.getAllRestitutions();
        }
        if (role.equals("user")) {
            listOfRestitutions = restitutionService.getRestitutionsByLogin(authUser.getLogin());
        }
        if (listOfRestitutions.size() == 0) {
            request.setAttribute("restitutionError", "You haven't rented any car yet!");
        }

        request.setAttribute("restitutions", listOfRestitutions);
        WebUtils.forward("return", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        String numberID  = request.getParameter("return");
        String accept = request.getParameter("accept");
        if (numberID != null) {
            String status = "Restituting...";
            restitutionService.updStatus(Long.parseLong(numberID), status);
        }
        if (accept != null) {
            String number = request.getParameter("accept");
            String status = "Approved!";
            String comment = request.getParameter("commentReturn");
            restitutionService.updStaComm(Long.parseLong(number), status, comment);
        }
        WebUtils.redirect("/return", request, response);
    }
}
