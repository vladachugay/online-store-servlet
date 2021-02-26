package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GetUsersPageCommand implements Command {
    private final UserService userService;

    public GetUsersPageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userService.getUsers());
        return "/WEB-INF/admin/users.jsp";
    }
}
