package com.vlados.controller.command;

import javax.servlet.http.HttpServletRequest;

public class GetLoginPageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/login.jsp";
    }
}
