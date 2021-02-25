package com.vlados.model.dao;

import com.vlados.model.entity.Order;
import com.vlados.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findByUser(User user);

    List<Order> findAllWithUsers();

    boolean payOrderById(long id);

    boolean cancelOrderById(long id);
}
