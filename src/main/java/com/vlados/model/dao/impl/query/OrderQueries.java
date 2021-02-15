package com.vlados.model.dao.impl.query;

public class OrderQueries {

    public static final String FIND_ALL_WITH_RELATIONSHIPS = "select * from orders o join users u using(user_id)";

}
