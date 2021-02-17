package com.vlados.controller.command;

import com.vlados.model.dto.ProductDTO;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EditProductCommand implements Command{

    private final ProductService productService;

    public EditProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        productService.editProduct(ProductDTO.builder()
                .id(Long.parseLong(parts[parts.length - 1]))
                .name(request.getParameter("name"))
                .amount(Integer.parseInt(request.getParameter("amount")))
                .category(request.getParameter("category"))
                .material(request.getParameter("material"))
                .date(LocalDateTime.now())
                .description(request.getParameter("description"))
                .price(new BigDecimal(request.getParameter("price")))
                .build());
        return "redirect:/admin/products";

    }
}
