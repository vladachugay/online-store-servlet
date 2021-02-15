package com.vlados.model.dao.mapper;

import com.vlados.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("user_id"))
                .email(rs.getString("email"))
                .fullName(rs.getString("full_name"))
                .username(rs.getString("username"))
                .locked(rs.getBoolean("locked"))
                .password(rs.getString("password"))
                .phoneNumber(rs.getString("phone_number"))
                .role(User.Role.valueOf(rs.getString("role")))
                .build();
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
