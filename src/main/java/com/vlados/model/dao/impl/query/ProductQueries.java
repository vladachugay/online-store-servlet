package com.vlados.model.dao.impl.query;

public class ProductQueries {

    public static final String FIND_ALL = "select * from products";
    public static final String CREATE = "insert into products (name, price, category, material," +
            " pic_path, date, description, amount) values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "update products set name = ?, " +
            "price = ?, " +
            "category = ?, " +
            "material = ?, " +
            "pic_path = ?, " +
            "description = ?," +
            "amount = ? " +
            "where product_id = ? ";

    public static final String FIND_BY_ID = "select * from products where product_id = ? ";

    public static final String DELETE_BY_ID = "delete from products where product_id = ?";

    public static final String INCREASE_AMOUNT = "update products set amount = amount + ? where product_id = ?";
    public static final String REDUCE_AMOUNT = "update products set amount = amount - ? where product_id = ?";
}
