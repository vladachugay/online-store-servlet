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
    public static final String GET_PRODUCTS_ID_BY_ORDER_ID = "select * from order_products where order_id = ?";
    /////////////////
    //   Partials
    /////////////////

    public static final String ORDER_BY_NEW_IN = " order by date ";
    public static final String ORDER_BY_NAME_ASC = " order by name ";
    public static final String ORDER_BY_NAME_DESC = " order by name desc ";
    public static final String ORDER_BY_PRICE_DESC = " order by price desc ";
    public static final String ORDER_BY_PRICE_ASC = " order by price  ";

    public static final String WHERE = " where ";
    public static final String AND = " and ";
    public static final String QUOTE = "'";

    public static final String CATEGORY_EQUALS = " category = ";
    public static final String MATERIAL_EQUALS = " material = ";
    public static final String PRICE_BETWEEN = " price between ";
}
