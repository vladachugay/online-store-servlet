package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class PayOrderCommand implements Command {
    private final OrderService orderService;

    public PayOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        orderService.payOrderById(Long.parseLong(parts[parts.length - 1]));
        return "redirect:/admin";
    }
}
