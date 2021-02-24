package com.vlados.controller.command;

import com.vlados.model.dto.ProductDTO;
import com.vlados.model.exception.StoreException;
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
        try {
            productService.deleteById(Long.parseLong(parts[parts.length - 1]));
        } catch (StoreException e) {
            request.setAttribute("error_message", e.getMessage());
            return "redirect:/admin/products?error_message="+e.getMessage();
        }
        return "redirect:/admin/products";
    }
}
