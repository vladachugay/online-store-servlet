package com.vlados.controller.command;

import javax.servlet.http.HttpServletRequest;

public class GetCartPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/"+request.getSession().getAttribute("role").toString().toLowerCase()+"/cart.jsp";
    }
}
