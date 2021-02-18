package com.vlados.controller.command;

import com.vlados.model.entity.User;
import com.vlados.model.service.OrderService;
import com.vlados.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GetAccountPage implements Command {

    private final UserService userService;
    private final OrderService orderService;

    public GetAccountPage(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().getAttribute("username");
        User user = userService.findByUserName((String) request.getSession().getAttribute("username"));
        request.setAttribute("user", user);
        request.setAttribute("orders", orderService.getOrdersByUserId(user));
        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/account.jsp";
    }
}
