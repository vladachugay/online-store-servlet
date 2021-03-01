package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.ProductDao;
import com.vlados.model.dto.ProductDTO;
import com.vlados.model.entity.Product;
import com.vlados.model.exception.store_exc.CantDeleteBecauseOfOrderException;
import com.vlados.model.exception.store_exc.DuplicateProductNameException;
import com.vlados.model.util.ExceptionKeys;
import com.vlados.model.util.Page;
import com.vlados.model.util.Pageable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceUnitTest {

    private static final String CATEGORY = "Category";
    private static final String SORT_CRITERIA = "Sort criteria";
    private static final String MATERIAL = "Material";
    private static final BigDecimal PRICE_FROM = BigDecimal.ONE;
    private static final BigDecimal PRICE_TO = BigDecimal.TEN;
    private static final long ID = 1;
    private static final long WRONG_ID = -1;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private Page<Product> page;
    @Mock
    private ProductDao productDao;
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private Pageable pageable;
    @Mock
    private ProductDTO productDTO;
    @Mock
    private Product product;
    @InjectMocks
    private ProductService testInstance;


    @Before
    public void setUp() {
        when(daoFactory.createProductDao()).thenReturn(productDao);
    }

    @Test
    public void shouldReturnFilteredProducts() {
        when(productDao.findFiltered(pageable, SORT_CRITERIA, CATEGORY, MATERIAL, PRICE_FROM, PRICE_TO))
                .thenReturn(page);

        Page<Product> result = testInstance.getFilteredProducts(pageable, SORT_CRITERIA, CATEGORY, MATERIAL, PRICE_FROM, PRICE_TO);

        assertEquals(page, result);
    }

    @Test
    public void shouldAddProduct() {
        when(productDao.create(any(Product.class))).thenReturn(true);

        boolean result = testInstance.addProduct(productDTO);

        assertTrue(result);
    }

    @Test
    public void shouldThrowDuplicateProductNameExceptionWhileAdding() {
        when(productDao.create(any(Product.class))).thenThrow(RuntimeException.class);
        thrown.expect(DuplicateProductNameException.class);
        thrown.expectMessage(ExceptionKeys.DUPLICATE_PRODUCT_NAME);

        testInstance.addProduct(productDTO);
    }

    @Test
    public void shouldReturnProductById() {
        Optional<Product> optionalProduct = Optional.of(product);
        when(productDao.findById(ID)).thenReturn(optionalProduct);

        Product result = testInstance.findById(ID);

        assertEquals(product, result);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenProductIdNotExist() {
        Optional<Product> optionalProduct = Optional.empty();
        when(productDao.findById(WRONG_ID)).thenReturn(optionalProduct);
        thrown.expect(RuntimeException.class);

        testInstance.findById(WRONG_ID);
    }

    @Test
    public void shouldEditProduct() {
        when(productDao.update(any(Product.class))).thenReturn(true);

        boolean result = testInstance.editProduct(productDTO);

        assertTrue(result);
    }

    @Test
    public void shouldThrowDuplicateProductNameExceptionWhileEditing() {
        when(productDao.update(any(Product.class))).thenThrow(RuntimeException.class);
        thrown.expect(DuplicateProductNameException.class);
        thrown.expectMessage(ExceptionKeys.DUPLICATE_PRODUCT_NAME);

        testInstance.editProduct(productDTO);
    }

    @Test
    public void shouldDeleteProduct() {
        when(productDao.delete(ID)).thenReturn(true);

        boolean result = testInstance.deleteById(ID);

        assertTrue(result);
    }

    @Test
    public void shouldThrowCantDeleteBecauseOfOrderException() {
        when(productDao.delete(ID)).thenThrow(RuntimeException.class);
        thrown.expect(CantDeleteBecauseOfOrderException.class);
        thrown.expectMessage(ExceptionKeys.CANT_DELETE_BECAUSE_OF_ORDER);

        testInstance.deleteById(ID);
    }
}
