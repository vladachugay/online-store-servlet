package com.vlados.controller.filter;

import com.vlados.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final String ADMIN = "/admin";
    private static final String USER = "/user";
    private static final String GUEST_DOMAIN = "^(/)$|^(/login)$|^(/registration)$";
    private static final String LOGOUT_DOMAIN = "/logout";
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session.getAttribute("role") == null) {
            session.setAttribute("role", User.Role.UNKNOWN.toString());
            session.setAttribute("username", "Guest");
        }

        User.Role role = User.Role.valueOf(session.getAttribute("role").toString());
        if (!checkAccess(req.getRequestURI(), role)) {
            if (role.equals(User.Role.UNKNOWN)) {
                resp.sendRedirect("/login");
                return;
            }
            resp.sendError(403);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkAccess(String uri, User.Role role) {
        switch (role) {
            case ADMIN:
                return checkAdminAccess(uri);
            case USER:
                return checkUserAccess(uri);
            default:
                return checkGuestAccess(uri);
        }
    }

    private boolean checkAdminAccess(String uri) {
        return uri.startsWith(ADMIN) || uri.equals(LOGOUT_DOMAIN);
    }

    private boolean checkUserAccess(String uri) {
        return uri.startsWith(USER) || uri.equals(LOGOUT_DOMAIN);
    }


    //TODO add URIs to user access
    private boolean checkGuestAccess(String uri) {
        return uri.matches(GUEST_DOMAIN);
    }

    @Override
    public void destroy() {

    }
}
