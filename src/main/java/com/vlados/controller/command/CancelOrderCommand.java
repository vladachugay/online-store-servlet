package com.vlados.controller.command;

import com.vlados.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements Command {


    private final OrderService orderService;

    public CancelOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        orderService.closeOrderById(Long.parseLong(parts[parts.length - 1]));
        return "redirect:/admin";
    }
}
