package com.vlados.model.dao;

import com.vlados.model.entity.Product;

public interface ProductDao extends GenericDao<Product>{

        void reduceAmountById(long id, int quantity);

        void increaseAmountById(long id, int quantity);


}
