package com.vlados.model.dao.impl;

import com.vlados.model.dao.ProductDao;
import com.vlados.model.dao.impl.query.ProductQueries;
import com.vlados.model.dao.mapper.ProductMapper;
import com.vlados.model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCProductDao implements ProductDao {
    private final Connection connection;
    private final ProductMapper productMapper = new ProductMapper();

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
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(productMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            //TODO log
            //TODO handle exception
            System.err.println(ex.getMessage());
        }
        return products;
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
