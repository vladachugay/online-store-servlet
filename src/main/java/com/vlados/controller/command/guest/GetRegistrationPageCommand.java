package com.vlados.controller.command.guest;

import com.vlados.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetRegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/guest/registration.jsp";
    }
}
