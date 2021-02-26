package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LockUserCommand implements Command {
    private final UserService userService;

    public LockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        userService.lockUser(Long.parseLong(parts[parts.length - 1]));
        return "redirect:/admin/users";
    }
}
