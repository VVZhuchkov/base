package com.github.vvzhuchkov.web.servlet;

import com.github.vvzhuchkov.model.AuthUser;
import com.github.vvzhuchkov.service.SecurityService;
import com.github.vvzhuchkov.service.impl.DefaultSecurityService;
import com.github.vvzhuchkov.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet (urlPatterns = {"/login"})
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
            response.sendRedirect(request.getContextPath() +"/employee");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");
        AuthUser user = securityService.login(id, password);

        if (user == null) {
            request.setAttribute("error", "login or password invalid");
            WebUtils.forward("login", request, response);
            logIn.info("Employee {} unsuccessfully attempted to log in at {}", id, LocalDateTime.now());
        }
        request.getSession().setAttribute("authUser", user);

        try {
            response.sendRedirect(request.getContextPath() +"/employee");
            logIn.info("Employee {} logged in at {}", id, LocalDateTime.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
