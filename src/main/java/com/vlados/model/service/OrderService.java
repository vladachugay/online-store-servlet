package com.vlados.model.service;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.OrderDao;
import com.vlados.model.entity.*;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    public void fillOrderProducts(Cart cart, Order order) {
        for (Map.Entry<Product, Integer> pair : cart.getCartProducts().entrySet()) {
            order.getOrderProducts().add(new OrderProducts(order, pair.getKey(), pair.getValue()));
        }
    }


    public boolean closeOrderById(long id) {
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

    private UserTransaction openTransaction() {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        try {
            userTransactionImp.begin();
        } catch (NotSupportedException | SystemException e) {
            throw new RuntimeException();
        }
        return userTransactionImp;
    }

    private void closeTransaction(UserTransaction userTransaction) {
        try {
            userTransaction.commit();
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception e1) {
            }
        }
    }
}
