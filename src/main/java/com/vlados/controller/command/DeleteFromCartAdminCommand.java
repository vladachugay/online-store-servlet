package com.vlados.controller.command;

import com.vlados.model.entity.Cart;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class DeleteFromCartAdminCommand implements Command {
    private final ProductService productService;

    public DeleteFromCartAdminCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String[] parts = request.getRequestURI().split("/");
        cart.deleteProduct(productService.findById(Long.parseLong(parts[parts.length - 1])));
        request.getSession().setAttribute("cart", cart);
        return "redirect:/admin/cart";
    }
}
