package com.vlados.controller.command;

import com.vlados.model.entity.Order;
import com.vlados.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class GetAdminPanelCommand implements Command{
    private final OrderService orderService;

    public GetAdminPanelCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders", orderService.getOrders());
        request.setAttribute("statuses", Order.Status.values());
        return "/WEB-INF/admin/adminPanel.jsp";
    }
}
