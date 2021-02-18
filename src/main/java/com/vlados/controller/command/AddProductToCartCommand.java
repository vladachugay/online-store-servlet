package com.vlados.controller.command;

import com.vlados.model.entity.Cart;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class AddProductToCartCommand implements Command {

    private final ProductService productService;

    public AddProductToCartCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String[] parts = request.getRequestURI().split("/");
        System.out.println(cart);
        cart.addProduct(productService.findById(Long.parseLong(parts[parts.length - 1])),
                Integer.parseInt(request.getParameter("quantity")));
        request.getSession().setAttribute("cart", cart);
        System.out.println(cart);
        return "redirect:/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/cart";
    }
}
