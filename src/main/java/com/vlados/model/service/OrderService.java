package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.OrderDao;
import com.vlados.model.entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final DaoFactory daoFactory;
    private final UserService userService;

    public OrderService(final DaoFactory daoFactory, final UserService userService) {
        this.daoFactory = daoFactory;
        this.userService = userService;
    }

    public List<Order> getOrders() {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findAllWithUsers();
        }
    }

    public boolean createOrder(Cart cart, String username) {
        Order order = Order.builder()
                .status(Order.Status.REGISTERED)
                .totalPrice(cart.getTotalPrice())
                .creationDate(LocalDateTime.now())
                .user(userService.findByUserName(username))
                .build();

        fillOrderProducts(cart, order);

        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.create(order);
        }
    }

    private void fillOrderProducts(Cart cart, Order order) {
        for (Map.Entry<Product, Integer> pair : cart.getCartProducts().entrySet()) {
            order.getOrderProducts().add(new OrderProducts(order, pair.getKey(), pair.getValue()));
        }
    }


    public boolean cancelOrderById(long id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.cancelOrderById(id);
        }
    }

    public boolean payOrderById(long id) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.payOrderById(id);
        }
    }

    public List<Order> getOrdersByUserId(User user) {
        try (OrderDao orderDao = daoFactory.createOrderDao()) {
            return orderDao.findByUser(user);
        }
    }
}
