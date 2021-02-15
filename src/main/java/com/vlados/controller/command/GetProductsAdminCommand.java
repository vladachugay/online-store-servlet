package com.vlados.controller.command;

import com.vlados.model.entity.Product;
import com.vlados.model.entity.SortCriteria;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class GetProductsAdminCommand implements Command {
    private final ProductService productService;

    public GetProductsAdminCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("products", productService.getProducts());
        request.setAttribute("sorting", SortCriteria.values());
        request.setAttribute("materials", Product.Material.values());
        request.setAttribute("categories", Product.Category.values());
        return "/WEB-INF/admin/products.jsp";
    }
}
