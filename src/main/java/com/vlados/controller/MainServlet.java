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
        //AUTH
        getCommands.put("", new IndexCommand());
        getCommands.put("login", new GetLoginPageCommand());
        getCommands.put("registration", new GetRegistrationPageCommand());
        getCommands.put("logout", new LogOutCommand());

        //MAIN FUNCTIONS
        getCommands.put("user/products", new GetProductsCommand(serviceFactory.createProductService()));
        getCommands.put("admin/products", new GetProductsCommand(serviceFactory.createProductService()));
        getCommands.put("guest/products", new GetProductsCommand(serviceFactory.createProductService()));

        getCommands.put("admin/products/\\d+", new GetProductPageCommand(serviceFactory.createProductService()));
        getCommands.put("user/products/\\d+", new GetProductPageCommand(serviceFactory.createProductService()));
        getCommands.put("guest/products/\\d+", new GetProductPageCommand(serviceFactory.createProductService()));

        getCommands.put("admin/cart", new GetCartPageCommand());
        getCommands.put("user/cart", new GetCartPageCommand());
        getCommands.put("guest/cart", new GetCartPageCommand());

        getCommands.put("admin/account", new GetAccountPage(serviceFactory.createUserService(), serviceFactory.createOrderService()));
        getCommands.put("user/account", new GetAccountPage(serviceFactory.createUserService(), serviceFactory.createOrderService()));

        // ADMIN
        getCommands.put("admin", new GetAdminPanelCommand(serviceFactory.createOrderService()));
        getCommands.put("admin/users", new GetUsersPageCommand(serviceFactory.createUserService()));
        getCommands.put("admin/products/add", new GetAddProductPage());
        getCommands.put("admin/products/edit/\\d+", new GetEditProductPageCommand(serviceFactory.createProductService()));
    }

    private void putPostCommands(ServiceFactory serviceFactory) {
        //AUTH
        postCommands.put("login", new LogInCommand(serviceFactory.createUserService()));
        postCommands.put("registration", new RegistrationCommand(serviceFactory.createUserService()));

        //MAIN FUNCTIONS
        postCommands.put("admin/cart/add/\\d+", new AddProductToCartCommand(serviceFactory.createProductService()));
        postCommands.put("user/cart/add/\\d+", new AddProductToCartCommand(serviceFactory.createProductService()));
        postCommands.put("guest/cart/add/\\d+", new AddProductToCartCommand(serviceFactory.createProductService()));

        postCommands.put("admin/cart/delete/\\d+", new DeleteProductFromCartCommand(serviceFactory.createProductService()));
        postCommands.put("user/cart/delete/\\d+", new DeleteProductFromCartCommand(serviceFactory.createProductService()));
        postCommands.put("guest/cart/delete/\\d+", new DeleteProductFromCartCommand(serviceFactory.createProductService()));

        postCommands.put("admin/orders/create", new CreateOrderCommand(serviceFactory.createOrderService()));
        postCommands.put("user/orders/create", new CreateOrderCommand(serviceFactory.createOrderService()));

        //ADMIN
        postCommands.put("admin/users/lock/\\d+", new LockUserCommand(serviceFactory.createUserService()));
        postCommands.put("admin/users/unlock/\\d+", new UnlockUserCommand(serviceFactory.createUserService()));
        postCommands.put("admin/products/add", new AddProductCommand(serviceFactory.createProductService()));
        postCommands.put("admin/products/edit/\\d+", new EditProductCommand(serviceFactory.createProductService()));
        postCommands.put("admin/products/delete/\\d+", new DeleteProductCommand(serviceFactory.createProductService()));
        postCommands.put("admin/orders/pay/\\d+", new PayOrderCommand(serviceFactory.createOrderService()));
        postCommands.put("admin/orders/cancel/\\d+", new CancelOrderCommand(serviceFactory.createOrderService()));

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
