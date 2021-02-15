package com.vlados.controller.command.guest;

import com.vlados.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetLoginPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/login.jsp";
    }
}
