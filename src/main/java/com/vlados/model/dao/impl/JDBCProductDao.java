package com.vlados.model.dao.impl;

import com.vlados.model.dao.ProductDao;
import com.vlados.model.entity.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCProductDao implements ProductDao {
    private final Connection connection;

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void reduceAmountById(long id, int quantity) {

    }

    @Override
    public void increaseAmountById(long id, int quantity) {

    }

    @Override
    public void create(Product entity) {

    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {

    }
}
