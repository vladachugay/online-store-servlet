package com.vlados.controller.command;

import com.vlados.model.dto.ProductDTO;
import com.vlados.model.entity.Product;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Enumeration;

public class AddProductCommand implements Command {
    private final ProductService productService;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("command");
        Enumeration<String> enumeration = request.getParameterNames();
        System.out.println(enumeration.hasMoreElements());
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
        productService.addProduct(ProductDTO.builder()
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
