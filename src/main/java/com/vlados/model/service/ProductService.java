package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;

public class ProductService {

    private final DaoFactory daoFactory;

    public ProductService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
