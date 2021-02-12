package com.vlados.model.dao.impl;

import com.vlados.model.dao.UserDao;
import com.vlados.model.dao.impl.query.UserQueries;
import com.vlados.model.dao.mapper.UserMapper;
import com.vlados.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            throw new RuntimeException();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {

    }
}
