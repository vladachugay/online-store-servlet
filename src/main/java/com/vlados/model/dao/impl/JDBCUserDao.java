package com.vlados.model.dao.impl;

import com.vlados.model.dao.UserDao;
import com.vlados.model.dao.impl.query.UserQueries;
import com.vlados.model.dao.mapper.UserMapper;
import com.vlados.model.entity.User;
import com.vlados.model.exception.store_exc.DuplicateUsernameException;
import com.vlados.model.util.ExceptionKeys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private final Connection connection;
    private final UserMapper userMapper = new UserMapper();


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("cant find user");
            throw new RuntimeException();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean lockUserById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.LOCK_USER)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException ex) {
            //TODO handle exception
            //TODO log
            System.err.println("cant lock user");
        }
        return false;
    }

    @Override
    public boolean unlockUserById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.UNLOCK_USER)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException ex) {
            //TODO handle exception
            //TODO log
            System.err.println("cant unlock user");
        }
        return false;
    }

    @Override
    public boolean create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.CREATE)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getRole().name());
            preparedStatement.setBoolean(7, user.isLocked());
            return preparedStatement.execute();
        } catch (SQLException e) {
            //TODO log
            System.err.println("Cant add new user");
            throw  new RuntimeException();
        }
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(UserQueries.FIND_ALL_SORTED);
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            //TODO log
            //TODO handle exception
            System.err.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public boolean update(User entity) {
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
