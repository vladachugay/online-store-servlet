package com.vlados.controller.command;

import com.vlados.model.entity.Cart;
import com.vlados.model.exception.StoreException;
import com.vlados.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {
    private final OrderService orderService;

    public CreateOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        try {
            orderService.createOrder(cart, (String)request.getSession().getAttribute("username"));
        } catch (StoreException e) {
            request.setAttribute("error_message", e.getMessage());
            return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/cart.jsp";
        }
        cart.clear();
        request.getSession().setAttribute("cart", cart);
        return "redirect:/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/cart";
    }
}
