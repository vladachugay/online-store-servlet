package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.model.entity.Product;

import javax.servlet.http.HttpServletRequest;

public class GetAddProductPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("categories", Product.Category.values());
        request.setAttribute("materials", Product.Material.values());
        return "/WEB-INF/admin/addProduct.jsp";
    }
}
