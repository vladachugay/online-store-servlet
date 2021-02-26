package com.vlados.controller.command.admin;

import com.vlados.controller.command.Command;
import com.vlados.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UnlockUserCommand implements Command {
    private final UserService userService;

    public UnlockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String[] parts = request.getRequestURI().split("/");
        userService.unlockUser(Long.parseLong(parts[parts.length - 1]));
        return "redirect:/admin/users";
    }
}
