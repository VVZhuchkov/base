package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/park")
public class ParkServlet extends HttpServlet {
    private CarService carService = DefaultCarService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        List<Car> allCars = carService.getAllCars();
        request.setAttribute("allCars", allCars);
        WebUtils.forward("park", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
