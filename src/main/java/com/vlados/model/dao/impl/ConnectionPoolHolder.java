package com.vlados.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.util.Properties;

public class ConnectionPoolHolder {

    private static final String DB_PROPERTIES =
            "C:\\Users\\ConnecT\\Desktop\\epam\\online-store-servlet\\src\\main\\resources\\db.properties";
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try (FileReader reader = new FileReader(DB_PROPERTIES)) {
                        Properties p = new Properties();
                        p.load(reader);
                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(p.getProperty("db.url"));
                        ds.setUsername(p.getProperty("db.username"));
                        ds.setPassword(p.getProperty("db.password"));
                        ds.setMinIdle(Integer.parseInt(p.getProperty("db.min.idle")));
                        ds.setMaxIdle(Integer.parseInt(p.getProperty("db.max.idle")));
                        ds.setMaxOpenPreparedStatements(Integer.parseInt(
                                p.getProperty("db.max.open.prepared.statement")));
                        ds.setDriverClassName(p.getProperty("db.driver.class.name"));
                        dataSource = ds;
                    } catch (Exception e) {
                        //TODO handle exception
                    }
                }
            }
        }
        return  dataSource;
    }
}
