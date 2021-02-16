package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.OrderDao;
import com.vlados.model.dao.ProductDao;
import com.vlados.model.entity.Order;

import java.util.List;

public class OrderService {

    private final DaoFactory daoFactory;
    private final ProductService productService;
    private final UserService userService;

    public OrderService(final DaoFactory daoFactory, final ProductService productService, final UserService userService) {
        this.daoFactory = daoFactory;
        this.productService = productService;
        this.userService = userService;
    }

    public List<Order> getOrders() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAllWithUsers();
        }
    }
}
