package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;

public class OrderService {

    private final DaoFactory daoFactory;
    private final ProductService productService;
    private final UserService userService;

    public OrderService(final DaoFactory daoFactory, final ProductService productService, final UserService userService) {
        this.daoFactory = daoFactory;
        this.productService = productService;
        this.userService = userService;
    }
}
