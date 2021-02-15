package com.vlados.controller.command.guest;

import com.vlados.controller.command.Command;
import com.vlados.controller.command.CommandUtility;
import com.vlados.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        String username = (String) session.getAttribute("username");
        CommandUtility.setUserRole(request, "Guest", User.Role.UNKNOWN);
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");

        System.out.println("Before logout");
        System.out.println(loggedUsers);

        loggedUsers.remove(username);
        context.setAttribute("loggedUsers", loggedUsers);


        System.out.println("After logout");
        System.out.println(loggedUsers);

        return "redirect:/login";
    }
}
