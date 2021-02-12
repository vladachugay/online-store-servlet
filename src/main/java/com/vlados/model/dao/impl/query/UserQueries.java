package com.vlados.model.dao.impl.query;

public class UserQueries {
    public static final String FIND_ALL = "select * from users";
    public static final String FIND_BY_USERNAME = FIND_ALL + " where username = ?";
}
