package com.vlados.model.dao;

import com.vlados.model.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product>{

        List<Product> findAll();

        boolean reduceAmountById(long id, int quantity);

        boolean increaseAmountById(long id, int quantity);


}
