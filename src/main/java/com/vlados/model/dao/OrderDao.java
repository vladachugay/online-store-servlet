package com.vlados.model.dao;

import com.vlados.model.entity.Order;
import com.vlados.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    void changeStatus(Long id, Order.Status status);

    List<Order> findByUser(User user);

    List<Order> findAllWithUsers();

    boolean addProductsToOrder(Order order) throws SQLException;

    long createAndGetNewId(Order order);
}
