package com.github.vvzhuchkov.web.servlet;

import com.github.vvzhuchkov.model.AuthUser;
import com.github.vvzhuchkov.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger logOut = LoggerFactory.getLogger(LogoutServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser)request.getSession().getAttribute("authUser");
                logOut.info("Employee {} logged out at {}", authUser.getId(), LocalDateTime.now());
        request.getSession().removeAttribute("authUser");
        request.getSession().invalidate();
        WebUtils.forward("login", request, response);
    }

}
