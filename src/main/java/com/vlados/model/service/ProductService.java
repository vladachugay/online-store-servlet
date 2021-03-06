package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.ProductDao;
import com.vlados.model.dto.ProductDTO;
import com.vlados.model.entity.Product;
import com.vlados.model.exception.store_exc.DuplicateProductNameException;
import com.vlados.model.exception.store_exc.CantDeleteBecauseOfOrderException;
import com.vlados.model.util.ExceptionKeys;
import com.vlados.model.util.Page;
import com.vlados.model.util.Pageable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class ProductService {

    private final DaoFactory daoFactory;
    private final Logger logger = LogManager.getLogger(ProductService.class);

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
            logger.error("{} while adding new product", e.getMessage());
            throw new DuplicateProductNameException(ExceptionKeys.DUPLICATE_PRODUCT_NAME);
        }
    }

    public Product findById(long id) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.findById(id).orElseThrow(() -> {
                logger.error("Cant find product with id {}", id);
                throw new RuntimeException();
            });
        }
    }

    public boolean editProduct(ProductDTO productDTO) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.update(new Product(productDTO));
        } catch (Exception e) {
            logger.error("{} while editing product with id {}", e.getMessage(), productDTO.getId());
            throw new DuplicateProductNameException(ExceptionKeys.DUPLICATE_PRODUCT_NAME);
        }
    }

    public boolean deleteById(long id) {
        try (ProductDao productDao = daoFactory.createProductDao()) {
            return productDao.delete(id);
        } catch (Exception e) {
            logger.error("{} while deleting product with id {}", e.getMessage(), id);
            throw new CantDeleteBecauseOfOrderException(ExceptionKeys.CANT_DELETE_BECAUSE_OF_ORDER);
        }
    }
}
