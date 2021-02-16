package com.vlados.model.dao.impl.query;

public class ProductQueries {

    public static final String FIND_ALL = "select * from products";
    public static final String CREATE = "insert into products (name, price, category, material," +
            " pic_path, date, description, amount) values (?, ?, ?, ?, ?, ?, ?, ?)";


}
