package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.AuthUser;
import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.model.Request;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.RoleUserService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.service.impl.DefaultRoleUserService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/city")
public class CityServlet extends HttpServlet {
    private CarService carService = DefaultCarService.getInstance();
    private RoleUserService roleUserService = DefaultRoleUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
        String role = roleUserService.getRoleUserByLogin(authUser.getLogin());
        request.setAttribute("role", role);
        Request mainReq = (Request) request.getSession().getAttribute("mainReq");
        List<Car> carList = carService.getByLocation(mainReq.getLocation());
        List<Car> availableCars = new ArrayList<>();
        for(Car car : carList){
            if (carService.getAvailableCars(car, mainReq)!=null)
        availableCars.add(carService.getAvailableCars(car, mainReq));}
        if (availableCars.size() == 0) {
            request.setAttribute("cityError", "We don't have available cars for this period of time!");
        }
        request.setAttribute("cars", availableCars);
        WebUtils.forward("city", request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        WebUtils.forward("/order", request, response);
    }
}
