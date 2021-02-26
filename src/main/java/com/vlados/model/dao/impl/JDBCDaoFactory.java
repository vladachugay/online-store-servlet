package com.vlados.model.dao.impl;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.OrderDao;
import com.vlados.model.dao.ProductDao;
import com.vlados.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private static final Logger logger = LogManager.getLogger(JDBCDaoFactory.class);

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public ProductDao createProductDao() {
        return new JDBCProductDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("{} while getting connection", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
