package com.vlados.model.dao.impl;

import com.vlados.model.dao.OrderDao;
import com.vlados.model.dao.impl.query.OrderQueries;
import com.vlados.model.dao.mapper.OrderMapper;
import com.vlados.model.entity.Order;
import com.vlados.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JDBCOrderDao implements OrderDao {
    private final Connection connection;
    private OrderMapper orderMapper = new OrderMapper();

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
    public List<Order> findAllWithUsers() {
        Map<Long, Order> orders = new HashMap<>();
        Map<Long, User> users = new HashMap<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(OrderQueries.FIND_ALL_WITH_USERS);
            while (rs.next()) {
                orderMapper.extractFromResultSetWithUsers(rs, orders, users);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //TODO log
            //TODO handle exception
        }

        return new ArrayList<>(orders.values());
    }

    @Override
    public boolean create(Order entity) {
        return false;
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
