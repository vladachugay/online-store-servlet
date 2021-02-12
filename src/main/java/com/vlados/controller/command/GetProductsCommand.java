package com.vlados.controller.command;

import javax.servlet.http.HttpServletRequest;

public class GetProductsCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/products.jsp";
    }
}
