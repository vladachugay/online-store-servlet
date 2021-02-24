package com.vlados.controller.command;

import com.vlados.controller.util.Validator;
import com.vlados.model.dto.ProductDTO;
import com.vlados.model.entity.Product;
import com.vlados.model.exception.StoreException;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class AddProductCommand implements Command {

    private static final String REDIRECT_PRODUCTS = "redirect:/admin/products";
    private static final String ERROR = "/WEB-INF/admin/addProduct.jsp";

    private final ProductService productService;

    public AddProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        ProductDTO productDTO = ProductDTO.builder()
                .name(request.getParameter("name"))
                .amount(Integer.parseInt(request.getParameter("amount")))
                .category(Product.Category.valueOf(request.getParameter("category")))
                .material(Product.Material.valueOf(request.getParameter("material")))
                .date(LocalDateTime.now())
                .description(request.getParameter("description"))
                .price(new BigDecimal(request.getParameter("price")))
                .build();

        Map<String, String> errors = Validator.validateProducts(productDTO);

        /////////////////////////////
        //these parameters are needed if there are some problems with thr product
        request.setAttribute("materials", Product.Material.values());
        request.setAttribute("categories", Product.Category.values());
        request.setAttribute("product", productDTO);
        ///////////////////////

        if (errors.size() > 0) {
            //TODO log
            request.setAttribute("errors", errors);
            return ERROR;
        }

        try {
            productService.addProduct(productDTO);
        }
        catch (StoreException e) {
            request.setAttribute("error_message", e.getMessage());
            return ERROR;
        }
        return REDIRECT_PRODUCTS;
    }
}
