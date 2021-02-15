package com.vlados.controller;

import com.vlados.controller.command.*;
import com.vlados.controller.command.guest.*;
import com.vlados.model.service.ServiceFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainServlet extends HttpServlet {
    private static final String REDIRECT = "redirect:";
    private final Map<String, Command> getCommands = new ConcurrentHashMap<>();
    private final Map<String, Command> postCommands = new ConcurrentHashMap<>();
    private static final String COMMAND_NOT_FOUND = "COMMAND NOT FOUND";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        config.getServletContext().setAttribute("loggedUsers", new HashSet<String>());
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        putGetCommands(serviceFactory);
        putPostCommands(serviceFactory);
    }

    private void putGetCommands(ServiceFactory serviceFactory) {
        getCommands.put("", new IndexCommand());
        getCommands.put("login", new GetLoginPageCommand());
        getCommands.put("user/products", new GetProductsUserCommand(serviceFactory.createProductService()));
        getCommands.put("admin/products", new GetProductsAdminCommand(serviceFactory.createProductService()));
        getCommands.put("products", new GetProductsUserCommand(serviceFactory.createProductService()));
        getCommands.put("registration", new GetRegistrationPageCommand());
        getCommands.put("logout", new LogOutCommand());
        getCommands.put("admin", new GetAdminPanelCommand(ServiceFactory));
    }

    private void putPostCommands(ServiceFactory serviceFactory) {
        postCommands.put("login", new LogInCommand(serviceFactory.createUserService()));
        postCommands.put("registration", new RegistrationCommand(serviceFactory.createUserService()));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, getCommands);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, postCommands);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response,
                                Map<String, Command> commands) throws IOException, ServletException {
        String path = request.getRequestURI();
        path = path.replaceFirst("/", "");
        String key = commands
                .keySet()
                .stream()
                .filter(path::matches)
                .findFirst()
                .orElse(COMMAND_NOT_FOUND);

        if (key.equals(COMMAND_NOT_FOUND)) {
            response.sendError(404);
            return;
        }

        Command command = commands.get(key);
        String result;
        try {
            result = command.execute(request);
        } catch (Exception e) {
            //todo exception
            return;
        }

        if (result.contains(REDIRECT)) {
            response.sendRedirect(result.replace(REDIRECT, ""));
        } else {
            request.getRequestDispatcher(result).forward(request, response);
        }
    }
}
