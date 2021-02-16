package com.vlados.model.dao.impl.query;

public class UserQueries {
    public static final String FIND_ALL = "select * from users";
    public static final String FIND_ALL_SORTED = "select * from users order by username";
    public static final String FIND_BY_USERNAME = FIND_ALL + " where username = ?";
    public static final String CREATE = "insert into users (full_name, username, password, phone_number, email, role, locked)" +
            " values (?, ?, ?, ?, ?, ?, ?)";
    public static final String LOCK_USER = "update users set locked = true where user_id = ?";
    public static final String UNLOCK_USER = "update users set locked = false where user_id = ?";
}
