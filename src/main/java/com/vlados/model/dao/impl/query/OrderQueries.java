package com.vlados.model.dao.impl.query;

import com.vlados.model.entity.Order;

public class OrderQueries {

    public static final String FIND_ALL_WITH_USERS = "select * from orders o join users u using(user_id)";

    public static final String CREATE = "insert into orders (status, total_price, creation_date, user_id) " +
            "values (?, ?, ?, ?)";
    public static final String ADD_PRODUCT_TO_ORDER = "insert into order_products (order_id, product_id, amount_in_order) values(?, ?, ?)";

    public static final String CANCEL_ORDER = "update orders set status = '"+ Order.Status.CANCELED.name() +"' where order_id = ? ";
    public static final String PAY_ORDER = "update orders set status = '"+ Order.Status.PAID.name() +"' where order_id = ? ";
    public static final String FIND_BY_USER = "select * from orders where user_id = ? ";
}
