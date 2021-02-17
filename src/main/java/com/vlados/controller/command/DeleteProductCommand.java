package com.vlados.controller.command;

import com.vlados.model.dto.ProductDTO;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class DeleteProductCommand implements Command {
    private final ProductService productService;

    public DeleteProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String[] parts = request.getRequestURI().split("/");
        productService.deleteById(Long.parseLong(parts[parts.length - 1]));
        return "redirect:/admin/products";
    }
}
