package com.vlados.model.dao.impl;

import com.vlados.model.dao.ProductDao;
import com.vlados.model.dao.impl.query.ProductQueries;
import com.vlados.model.dao.mapper.ProductMapper;
import com.vlados.model.entity.Product;

import java.sql.*;
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
    public boolean create(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.CREATE)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory().name());
            preparedStatement.setString(4, product.getMaterial().name());
            preparedStatement.setString(5, product.getPicPath());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(product.getDate()));
            preparedStatement.setString(7, product.getDescription());
            preparedStatement.setInt(8, product.getAmount());
            System.out.println("dao statement: " + preparedStatement);
            return preparedStatement.execute();
        } catch (SQLException e) {
            //TODO handle exception
            System.err.println("cant add new product");
        }
        return false;
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
