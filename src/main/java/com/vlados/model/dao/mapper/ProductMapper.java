package com.vlados.model.dao.mapper;

import com.vlados.model.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProductMapper implements ObjectMapper<Product> {
    @Override
    public Product extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Product makeUnique(Map<Long, Product> cache, Product entity) {
        return null;
    }
}
