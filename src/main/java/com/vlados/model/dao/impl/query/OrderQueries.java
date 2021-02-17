package com.vlados.model.dao.impl.query;

public class OrderQueries {

    public static final String FIND_ALL_WITH_USERS = "select * from orders o join users u using(user_id)";

    public static final String CREATE = "insert into orders (status, total_price, creation_date, user_id) " +
            "values (?, ?, ?, ?)";
    public static final String ADD_PRODUCT_TO_ORDER = "insert into order_products (order_id, product_id, amount_in_order) values(?, ?, ?)";

}
