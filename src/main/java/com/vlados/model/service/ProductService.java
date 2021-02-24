package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.ProductDao;
import com.vlados.model.dto.ProductDTO;
import com.vlados.model.entity.Product;
import com.vlados.model.entity.SortCriteria;
import com.vlados.model.exception.DuplicateProductNameException;
import com.vlados.model.exception.StoreException;
import com.vlados.model.exception.store_exc.CantDeleteBecauseOfOrderException;
import com.vlados.model.util.ExceptionKeys;
import com.vlados.model.util.Page;
import com.vlados.model.util.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final DaoFactory daoFactory;

    public ProductService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Page<Product> getFilteredProducts(Pageable pageable, String sortCriteria, String category,
                                             String material, BigDecimal priceFrom, BigDecimal priceTo) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findFiltered(pageable, sortCriteria, category, material, priceFrom, priceTo);
        }
    }

    public boolean addProduct(ProductDTO productDTO) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.create(new Product(productDTO));
        } catch (Exception e) {
            throw new DuplicateProductNameException(ExceptionKeys.DUPLICATE_PRODUCT_NAME);
        }
    }

    public Product findById(long id) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findById(id).get();
            //TODO check optional
        }
    }

    public boolean editProduct(ProductDTO productDTO) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.update(new Product(productDTO));
        } catch (Exception e) {
            //TODO log
            throw new DuplicateProductNameException(ExceptionKeys.DUPLICATE_PRODUCT_NAME);
        }
    }

    public boolean deleteById(long id) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.delete(id);
        } catch (Exception e) {
            //TODO log
            throw new CantDeleteBecauseOfOrderException(ExceptionKeys.CANT_DELETE_BECAUSE_OF_ORDER);
        }
    }

    public boolean reduceAmountById(long id, int quantity) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.reduceAmountById(id, quantity);
        }
    }

    public boolean increaseAmountById(long id, int quantity) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.increaseAmountById(id, quantity);
        }
    }
}
