package com.vlados.model.service;

import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.OrderDao;
import com.vlados.model.entity.Cart;
import com.vlados.model.entity.Order;
import com.vlados.model.entity.Product;
import com.vlados.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

    private static final long ID = 1;
    private static final String USERNAME = "username";
    private static final BigDecimal TOTAL_PRICE = BigDecimal.ONE;
    private static final Integer PRODUCT_AMOUNT = 1;

    @Mock
    private User user;
    @Mock
    private Cart cart;
    @Mock
    private Product product;
    @Mock
    private UserService userService;
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private OrderDao orderDao;
    @InjectMocks
    private OrderService testInstance;


    @Before
    public void setUp() {
        when(daoFactory.createOrderDao()).thenReturn(orderDao);
    }

    @Test
    public void shouldReturnAllOrders() {
        List<Order> orders = new ArrayList<>();
        when(orderDao.findAllWithUsers()).thenReturn(orders);

        List<Order> result = testInstance.getOrders();

        assertEquals(orders, result);
    }

    @Test
    public void shouldCancelOrderById() {
        when(orderDao.cancelOrderById(ID)).thenReturn(true);

        boolean result = testInstance.cancelOrderById(ID);

        assertTrue(result);
    }

    @Test
    public void shouldPayOrderById() {
        when(orderDao.payOrderById(ID)).thenReturn(true);

        boolean result = testInstance.payOrderById(ID);

        assertTrue(result);
    }

    @Test
    public void shouldReturnOrdersByUser() {
        List<Order> orders = new ArrayList<>();
        when(orderDao.findByUser(user)).thenReturn(orders);

        List<Order> result = testInstance.getOrdersByUserId(user);

        assertEquals(orders, result);
    }

    @Test
    public void shouldCreateOrder() {
        Map<Product, Integer> productMap = new HashMap<>();
        productMap.put(product, PRODUCT_AMOUNT);
        when(userService.findByUserName(USERNAME)).thenReturn(user);
        when(cart.getTotalPrice()).thenReturn(TOTAL_PRICE);
        when(cart.getCartProducts()).thenReturn(productMap);
        when(orderDao.create(any(Order.class))).thenReturn(true);

        boolean result = testInstance.createOrder(cart, USERNAME);

        assertTrue(result);
    }


}
