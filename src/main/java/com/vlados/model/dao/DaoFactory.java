package com.vlados.model.dao;

import com.vlados.model.dao.impl.JDBCDaoFactory;
import com.vlados.model.entity.Product;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract OrderDao createOrderDao();

    public abstract ProductDao createProductDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

}
