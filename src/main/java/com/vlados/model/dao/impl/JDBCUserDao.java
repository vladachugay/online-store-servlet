package com.vlados.model.dao.impl;

import com.vlados.model.dao.UserDao;
import com.vlados.model.dao.impl.query.UserQueries;
import com.vlados.model.dao.mapper.UserMapper;
import com.vlados.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private final Connection connection;
    private final UserMapper userMapper = new UserMapper();
    private final static Logger logger = LogManager.getLogger(JDBCUserDao.class);


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
            logger.error("{} while trying to find user by username {}", e.getMessage(), username);
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
            logger.error("{} while trying to lock user with id {}", ex.getMessage(), id);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean unlockUserById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.UNLOCK_USER)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException ex) {
            logger.error("{} while trying to unlock user with id {}", ex.getMessage(), id);
            throw new RuntimeException();
        }
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
            logger.error("{} while creating new user", e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<User> findById(long id) {
        throw new UnsupportedOperationException();
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
            logger.error("{} while trying to find all products", ex.getMessage());
        }
        return users;
    }

    @Override
    public boolean update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("{} while trying to close connection", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
