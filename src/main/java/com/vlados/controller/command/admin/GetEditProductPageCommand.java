package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.model.entity.Product;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class GetEditProductPageCommand implements Command {

    private final ProductService productService;

    public GetEditProductPageCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        request.setAttribute("product", productService.findById(Long.parseLong(parts[parts.length - 1])));
        request.setAttribute("categories", Product.Category.values());
        request.setAttribute("materials", Product.Material.values());
        return "/WEB-INF/admin/editProduct.jsp";
    }
}
