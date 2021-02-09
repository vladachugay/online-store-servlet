package com.vlados.model.dao.mapper;

import com.vlados.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order>{
    @Override
    public Order extractWithoutRelationsFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order entity) {
        return null;
    }
}
