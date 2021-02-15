package com.vlados.model.dao;

import com.vlados.model.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product>{

        List<Product> findAll();

        void reduceAmountById(long id, int quantity);

        void increaseAmountById(long id, int quantity);


}
