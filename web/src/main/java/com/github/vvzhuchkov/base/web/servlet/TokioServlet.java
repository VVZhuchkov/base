package com.github.vvzhuchkov.base.web.servlet;

import com.github.vvzhuchkov.base.model.Car;
import com.github.vvzhuchkov.base.service.CarService;
import com.github.vvzhuchkov.base.service.impl.DefaultCarService;
import com.github.vvzhuchkov.base.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/tokio")
public class TokioServlet extends HttpServlet {
    private CarService carService = DefaultCarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<Car> cars = carService.getByLocation("Tokio");
        request.setAttribute("cars", cars);
        WebUtils.forward("tokio", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
