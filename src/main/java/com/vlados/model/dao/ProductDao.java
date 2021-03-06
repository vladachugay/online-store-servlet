package com.vlados.model.dao;

import com.vlados.model.entity.Product;
import com.vlados.model.entity.SortCriteria;
import com.vlados.model.util.Page;
import com.vlados.model.util.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao extends GenericDao<Product> {

    List<Product> findAll();

//    boolean reduceAmountById(long id, int quantity);
//
//    boolean increaseAmountById(long id, int quantity);

    Page<Product> findFiltered(Pageable pageable, String sortCriteria, String category,
                               String material, BigDecimal priceFrom, BigDecimal priceTo);

}
