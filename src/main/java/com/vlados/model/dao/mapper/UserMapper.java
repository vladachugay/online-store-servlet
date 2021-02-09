package com.vlados.model.dao.mapper;

import com.vlados.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractWithoutRelationsFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User entity) {
        return null;
    }
}
