package com.vlados.controller.listener;

import com.vlados.model.entity.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("cart", new Cart());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) se
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String userName = (String) se.getSession().getAttribute("username");
        loggedUsers.remove(userName);
        se.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
