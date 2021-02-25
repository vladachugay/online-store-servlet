package com.vlados.model.dao.mapper;

import com.vlados.model.entity.Order;
import com.vlados.model.entity.OrderProducts;
import com.vlados.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderProductsMapper implements ObjectMapper<OrderProducts>{
    @Override
    public OrderProducts extractFromResultSet(ResultSet rs) throws SQLException {
        return new OrderProducts(Order.builder().id(rs.getLong("order_id")).build(),
                Product.builder().id(rs.getLong("product_id")).build(),
                rs.getInt("amount_in_order"));
    }

    @Override
    public OrderProducts makeUnique(Map<Long, OrderProducts> cache, OrderProducts entity) {
        return null;
    }
}
