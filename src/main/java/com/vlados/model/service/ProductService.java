package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.ProductDao;
import com.vlados.model.entity.Product;

import java.util.List;

public class ProductService {

    private final DaoFactory daoFactory;

    public ProductService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Product> getProducts() {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findAll();
        }
    }
}
