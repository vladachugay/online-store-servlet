package com.vlados.controller.command;

import javax.servlet.http.HttpServletRequest;

public class GetAdminCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/cart.jsp";
    }
}
