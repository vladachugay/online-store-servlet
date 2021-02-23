package com.vlados.controller.command;

import com.vlados.model.entity.User;
import com.vlados.model.exception.store_exc.login_exc.UserIsLockedException;
import com.vlados.model.util.ExceptionKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {

    public static void setUserRole(HttpServletRequest request, String username, User.Role role) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", role);
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, String username) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute("loggedUsers");

        if (loggedUsers.stream().anyMatch(username::equals)) {
            return true;
//            throw  new UserIsLockedException(ExceptionKeys.USER_LOGGED);
        }
        loggedUsers.add(username);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
