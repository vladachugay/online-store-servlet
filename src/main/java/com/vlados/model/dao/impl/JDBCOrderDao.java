package com.vlados.model.dao.impl;

import com.vlados.model.dao.OrderDao;
import com.vlados.model.entity.Order;
import com.vlados.model.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {
    private final Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void changeStatus(Long id, Order.Status status) {

    }

    @Override
    public List<Order> findByUser(User user) {
        return null;
    }

    @Override
    public void create(Order entity) {

    }

    @Override
    public Optional<Order> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {

    }
}
