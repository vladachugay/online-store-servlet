package com.vlados.model.dao.mapper;

import com.vlados.model.entity.Order;
import com.vlados.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order>{
    private final UserMapper userMapper = new UserMapper();

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        return Order.builder()
                .id(rs.getLong("order_id"))
                .totalPrice(rs.getBigDecimal("total_price"))
                .creationDate(rs.getTimestamp("creation_date").toLocalDateTime())
                .status(Order.Status.valueOf(rs.getString("status")))
                .build();
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }

    public Order extractFromResultSetWithUsers(ResultSet rs,
                                               Map<Long, Order> orders,
                                               Map<Long, User> users) throws SQLException {
        Order order = makeUnique(orders, extractFromResultSet(rs));
        order.setUser(userMapper.makeUnique(users, userMapper.extractFromResultSet(rs)));
        return order;
    }
}
