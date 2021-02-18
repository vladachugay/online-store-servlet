package com.vlados.controller.command.guest;

import com.vlados.controller.command.Command;
import com.vlados.controller.command.CommandUtility;
import com.vlados.model.entity.User;
import com.vlados.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LogInCommand implements Command {

    private static final String USER_REDIRECT = "redirect:/user/products" ;
    private static final String ADMIN_REDIRECT = "redirect:/admin/products";
    private static final String GUEST_REDIRECT = "redirect:/guest/products";
    private final UserService userService;

    public LogInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        User.Role role = null;
        try {
            role = userService.checkUserAndGetRole(username, pass);
        } catch (Exception e) {
            System.err.println("");
            //TODO: handle exception
        }

        if (CommandUtility.checkUserIsLogged(request, username)) {
            //TODO handle exception
            System.err.println("user has already logged in");
            return "login.jsp";
        }
        CommandUtility.setUserRole(request, username, role);
        switch (role) {
            case USER: return USER_REDIRECT;
            case ADMIN: return ADMIN_REDIRECT;
            default: return GUEST_REDIRECT;
        }
    }
}
