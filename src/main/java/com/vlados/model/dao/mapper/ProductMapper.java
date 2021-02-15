package com.vlados.model.dao.mapper;

import com.vlados.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .category(Product.Category.valueOf(rs.getString("category")))
                .material(Product.Material.valueOf(rs.getString("material")))
                .date(rs.getTimestamp("date").toLocalDateTime())
                .picPath(rs.getString("pic_path"))
                .description(rs.getString("description"))
                .price(rs.getBigDecimal("price"))
                .amount(rs.getInt("amount"))
                .build();
    }

    @Override
    public Product makeUnique(Map<Long, Product> cache, Product entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
