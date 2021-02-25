package com.vlados.model.dao.impl;

import com.vlados.model.dao.OrderDao;
import com.vlados.model.dao.impl.query.OrderQueries;
import com.vlados.model.dao.impl.query.ProductQueries;
import com.vlados.model.dao.mapper.OrderMapper;
import com.vlados.model.dao.mapper.OrderProductsMapper;
import com.vlados.model.entity.Order;
import com.vlados.model.entity.OrderProducts;
import com.vlados.model.entity.User;
import com.vlados.model.exception.StoreException;
import com.vlados.model.exception.store_exc.NotEnoughProductsException;
import com.vlados.model.util.ExceptionKeys;

import java.sql.*;
import java.util.*;

public class JDBCOrderDao implements OrderDao {
    private final Connection connection;
    private final OrderMapper orderMapper = new OrderMapper();
    private final OrderProductsMapper opMapper = new OrderProductsMapper();


    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findByUser(User user) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.FIND_BY_USER)) {
            preparedStatement.setLong(1, user.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orders.add(orderMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            System.err.println("cant get user's orders");
            //TODO log
            //TODO handle exception
        }
        return orders;
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
    public boolean create(Order order) {
        try (PreparedStatement statement1 = connection.prepareStatement(
                OrderQueries.CREATE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement2 = connection.prepareStatement(OrderQueries.ADD_PRODUCT_TO_ORDER)) {
            connection.setAutoCommit(false);

            setOrderAttributesForCreate(statement1, order).execute();
            ResultSet rs = statement1.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getLong(1));
            }

            for (OrderProducts op : order.getOrderProducts()) {
                setAttributesForOrderProducts(statement2, op).execute();
            }

            try (PreparedStatement statement3 = connection.prepareStatement(ProductQueries.REDUCE_AMOUNT)) {
                for (OrderProducts op : order.getOrderProducts()) {
                    setAttributesForProduct(statement3, op.getProduct().getId(), op.getAmount()).execute();
                }
            } catch (SQLException e) {
                throw new NotEnoughProductsException(ExceptionKeys.NOT_ENOUGH_PRODUCTS);
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback(connection);
            throw new RuntimeException();
        } catch (StoreException e) {
            rollback(connection);
            throw new StoreException(e.getMessage());
        }
    }

    @Override
    public boolean cancelOrderById(long id) {
        try (PreparedStatement statement1 = connection.prepareStatement(OrderQueries.CANCEL_ORDER);
             PreparedStatement statement2 = connection.prepareStatement(ProductQueries.INCREASE_AMOUNT);
             PreparedStatement statement3 = connection.prepareStatement(ProductQueries.GET_PRODUCTS_ID_BY_ORDER_ID)) {
            connection.setAutoCommit(false);

            statement1.setLong(1, id);
            statement1.execute();

            statement3.setLong(1, id);
            ResultSet rs = statement3.executeQuery();
            while (rs.next()) {
                OrderProducts op = opMapper.extractFromResultSet(rs);
                setAttributesForProduct(statement2, op.getProduct().getId(), op.getAmount()).execute();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback(connection);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean payOrderById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.PAY_ORDER)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Cant pay product ");
        }
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

    private PreparedStatement setOrderAttributesForCreate(PreparedStatement ps, Order order) throws SQLException {
        ps.setString(1, order.getStatus().name());
        ps.setBigDecimal(2, order.getTotalPrice());
        ps.setTimestamp(3, Timestamp.valueOf(order.getCreationDate()));
        ps.setLong(4, order.getUser().getId());
        return ps;
    }

    private PreparedStatement setAttributesForOrderProducts(PreparedStatement ps, OrderProducts op) throws SQLException {
        ps.setLong(1, op.getOrder().getId());
        ps.setLong(2, op.getProduct().getId());
        ps.setLong(3, op.getAmount());
        return ps;
    }

    private PreparedStatement setAttributesForProduct(PreparedStatement ps, Long id, int quantity) throws SQLException {
        ps.setInt(1, quantity);
        ps.setLong(2, id);
        return ps;
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new RuntimeException();
        }
    }
}
