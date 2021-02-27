package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.controller.command.CommandUtility;
import com.vlados.controller.util.Validator;
import com.vlados.model.dto.ProductDTO;
import com.vlados.model.exception.StoreException;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class EditProductCommand implements Command {

    private static final String ERROR = "/WEB-INF/admin/editProduct.jsp";
    private static final String REDIRECT_PRODUCTS = "redirect:/admin/products";

    private final ProductService productService;

    public EditProductCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");

        ProductDTO productDTO = CommandUtility.buildProductDtoFromRequest(request);
        productDTO.setId(Long.parseLong(parts[parts.length - 1]));

        Map<String, String> errors = Validator.validateProducts(productDTO);

        if (errors.size() > 0) {
            CommandUtility.setAttributesIfProblemsWithProductDTO(request, productDTO);
            request.setAttribute("errors", errors);
            return ERROR;
        }

        try {
            productService.editProduct(productDTO);
        } catch (StoreException e) {
            CommandUtility.setAttributesIfProblemsWithProductDTO(request, productDTO);
            request.setAttribute("error_message", e.getMessage());
            return ERROR;
        }
        return REDIRECT_PRODUCTS;
    }
}
