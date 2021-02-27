package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.controller.command.CommandUtility;
import com.vlados.controller.util.Validator;
import com.vlados.model.dto.ProductDTO;
import com.vlados.model.exception.StoreException;
import com.vlados.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
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
        ProductDTO productDTO = CommandUtility.buildProductDtoFromRequest(request);

        Map<String, String> errors = Validator.validateProducts(productDTO);

        if (errors.size() > 0) {
            CommandUtility.setAttributesIfProblemsWithProductDTO(request, productDTO);
            request.setAttribute("errors", errors);
            return ERROR;
        }

        try {
            productService.addProduct(productDTO);
        }
        catch (StoreException e) {
            CommandUtility.setAttributesIfProblemsWithProductDTO(request, productDTO);
            request.setAttribute("error_message", e.getMessage());
            return ERROR;
        }
        return REDIRECT_PRODUCTS;
    }
}
