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

@WebServlet("/car")
public class CarServlet extends HttpServlet {
    private CarService carService = DefaultCarService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
    WebUtils.forward("car", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        String photo = request.getParameter("photo");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String year = request.getParameter("year");
        String engine = request.getParameter("engine");
        String location = request.getParameter("location");
        String price = request.getParameter("price");
        String availability = request.getParameter("availability");
        Car car = new Car(photo, brand, model, Long.parseLong(year), engine, Long.parseLong(price), location, availability);
        carService.saveNewCar(car);
        WebUtils.redirect("/park", request, response);
    }
}
