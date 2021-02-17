package com.vlados.controller.command;

import com.vlados.model.entity.Cart;
import com.vlados.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CreateOrderAdminCommand implements Command {
    private final OrderService orderService;

    public CreateOrderAdminCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        orderService.createOrder(cart, (String)request.getSession().getAttribute("username"));
        cart.clear();
        request.getSession().setAttribute("cart", cart);
        return "redirect:/admin/cart";
    }
}
