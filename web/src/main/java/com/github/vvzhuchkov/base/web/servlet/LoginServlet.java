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

@WebServlet (urlPatterns = {"/index.html", "/login"})
public class LoginServlet extends HttpServlet {
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
        AuthUser user = securityService.login(login, password);

        if (user == null) {
            request.setAttribute("error", "Login or password invalid");
            WebUtils.forward("login", request, response);
            logIn.info("Employee {} unsuccessfully attempted to log in at {}", login, LocalDateTime.now());
            return;
        }
        request.getSession().setAttribute("authUser", user);

        try {
            logIn.info("User {} logged in at {}", login, LocalDateTime.now());
            response.sendRedirect(request.getContextPath() +"/main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
