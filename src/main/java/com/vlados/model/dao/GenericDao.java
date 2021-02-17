package com.vlados.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {

    boolean create(T entity);

    Optional<T> findById(long id);

    List<T> findAll();

    boolean update(T entity);

    boolean delete(long id);

    void close();
}
