package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.service.SecurityService;
import com.github.vvzhuchkov.base.service.impl.DefaultSecurityService;
import com.github.vvzhuchkov.base.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet (urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private SecurityService securityService = DefaultSecurityService.getInstance();
    private static final Logger logIn = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Object authUser = request.getSession().getAttribute("authUser");
        if (authUser == null) {
            WebUtils.forward("login", request, response);
        }
        try {
            response.sendRedirect(request.getContextPath() +"/main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        AuthUser user = securityService.registration(login, password,email);
            if (user == null) {
            request.setAttribute("error", "This login has been already registered.");
            WebUtils.forward("login", request, response);
            logIn.info("User {} unsuccessfully attempted to register at {}", login, LocalDateTime.now());
        }
            if (user.getEmail() == null) {
            request.setAttribute("error", "This email has been already registered.");
            WebUtils.forward("login", request, response);
            logIn.info("User {} unsuccessfully attempted to register at {}", login, LocalDateTime.now());
        }
            securityService.saveNewRegUser(user);
        request.getSession().setAttribute("authUser", user);
        try {
            response.sendRedirect(request.getContextPath() +"/main");
            logIn.info("User {} logged in at {}", login, LocalDateTime.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
