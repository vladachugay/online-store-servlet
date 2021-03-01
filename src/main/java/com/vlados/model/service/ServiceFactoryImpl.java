package com.vlados.model.service;

import com.vlados.controller.util.MD5PasswordEncoder;
import com.vlados.model.dao.DaoFactory;

public class ServiceFactoryImpl extends ServiceFactory {
    @Override
    public UserService createUserService() {
        return new UserService(DaoFactory.getInstance(), new MD5PasswordEncoder());
    }

    @Override
    public OrderService createOrderService() {
        return new OrderService(DaoFactory.getInstance(), createUserService());
    }

    @Override
    public ProductService createProductService() {
        return new ProductService(DaoFactory.getInstance());
    }
}
