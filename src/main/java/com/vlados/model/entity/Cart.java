package com.vlados.model.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Product, Integer> cartProducts;
    BigDecimal totalPrice;

    @Override
    public String toString() {
        return "Cart{" +
                "cartProducts=" + cartProducts +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Map<Product, Integer> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Map<Product, Integer> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart() {
        cartProducts = new HashMap<>();
        totalPrice = BigDecimal.ZERO;
    }

    public void addProduct(Product product, Integer quantity) {
        cartProducts.merge(product, quantity, Integer::sum);
        totalPrice = findTotalPrice();
    }

    public void deleteProduct(Product product) {
        cartProducts.remove(product);
        totalPrice = findTotalPrice();
    }

    public void clear() {
        cartProducts.clear();
        totalPrice = BigDecimal.valueOf(0);
    }

    public BigDecimal findTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Product, Integer> pair : cartProducts.entrySet()) {
            totalPrice = totalPrice.add(pair.getKey().getPrice().multiply(new BigDecimal(pair.getValue())));
        }

        return totalPrice;
    }
}
