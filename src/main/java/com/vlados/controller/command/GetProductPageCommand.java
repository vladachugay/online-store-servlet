package com.vlados.controller.command;

import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class GetProductPageCommand implements Command {

    private final ProductService productService;

    public GetProductPageCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        request.setAttribute("product", productService.findById(Long.parseLong(parts[parts.length - 1])));
        return "/WEB-INF/admin/product.jsp";
    }
}
