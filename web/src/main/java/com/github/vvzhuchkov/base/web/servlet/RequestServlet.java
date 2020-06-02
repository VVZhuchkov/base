package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Contact;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.ContactService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.service.impl.DefaultContactService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();
    private ContactService contactService = DefaultContactService.getInstance();
    private CarService carService = DefaultCarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        request.setAttribute("timeNow", dtf.format(timeNow));
        boolean hasContact;
        if (contactService.getContactByLogin(authUser.getLogin())==null){
            hasContact=false;
        }
        else{
            hasContact=true;
        }
        request.setAttribute("hasContact", hasContact);
        WebUtils.forward("request", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        String location = request.getParameter("location");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        request.setAttribute("timeNow", dtf.format(timeNow));
        LocalDate pickup = Date.valueOf(request.getParameter("pickup")).toLocalDate();
        LocalDate dropoff = Date.valueOf(request.getParameter("dropoff")).toLocalDate();
        if (pickup.isAfter(dropoff)) {
            request.setAttribute("datesError", "Incorrect dates!");
            WebUtils.forward("request", request, response);
        }
        Request mainReq = new Request(authUser.getLogin(), location, pickup, dropoff);
        request.getSession().setAttribute("mainReq", mainReq);
        if (contactService.getContactByLogin(authUser.getLogin())==null){
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String passport = request.getParameter("passport");
            if (name != null && surname != null && passport != null) {
                Contact contact = new Contact(authUser.getLogin(), surname.toUpperCase(), name.toUpperCase(), passport);
                contactService.saveNewContact(contact);
            }}
        WebUtils.redirect("/city", request, response);
    }
}
