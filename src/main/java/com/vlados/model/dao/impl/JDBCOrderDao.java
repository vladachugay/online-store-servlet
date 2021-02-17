package com.vlados.model.dao.impl;

import com.vlados.model.dao.OrderDao;
import com.vlados.model.dao.impl.query.OrderQueries;
import com.vlados.model.dao.impl.query.ProductQueries;
import com.vlados.model.dao.mapper.OrderMapper;
import com.vlados.model.entity.Order;
import com.vlados.model.entity.OrderProducts;
import com.vlados.model.entity.Product;
import com.vlados.model.entity.User;

import java.sql.*;
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
    public long createAndGetNewId(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(
                OrderQueries.CREATE, Statement.RETURN_GENERATED_KEYS)) {
//            "insert into orders (status, total_price, creation_date, user_id) " +
//                    "values (?, ?, ?, ?)"
            statement.setString(1, order.getStatus().name());
            statement.setBigDecimal(2, order.getTotalPrice());
            statement.setTimestamp(3, Timestamp.valueOf(order.getCreationDate()));
            statement.setLong(4, order.getUser().getId());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("cant add new order");
            throw new RuntimeException();
        }
        throw new RuntimeException();
    }

    public boolean addProductsToOrder(Order order) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.ADD_PRODUCT_TO_ORDER)) {
            connection.setAutoCommit(false);
            for (OrderProducts op : order.getOrderProducts()) {
                preparedStatement.setLong(1, op.getOrder().getId());
                preparedStatement.setLong(2, op.getProduct().getId());
                preparedStatement.setLong(3, op.getAmount());
                preparedStatement.execute();
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
            System.err.println("cant add products to order");
            connection.rollback();
            return false;
        }
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
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {

        return false;
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
