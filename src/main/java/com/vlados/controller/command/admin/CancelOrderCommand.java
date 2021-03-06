package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
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
        orderService.cancelOrderById(Long.parseLong(parts[parts.length - 1]));
        return "redirect:/admin";
    }
}
