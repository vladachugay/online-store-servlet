package com.vlados.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {
    private Long id;
    private BigDecimal totalPrice;
    private LocalDateTime creationDate;
    private Status status;
    private User user;
    private Set<OrderProducts> orderProducts;

    public enum Status {
        REGISTERED,
        CANCELED,
        PAID
    }

    public Order(Long id, BigDecimal totalPrice, LocalDateTime creationDate, Status status, User user, Set<OrderProducts> orderProducts) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
        this.status = status;
        this.user = user;
        this.orderProducts = orderProducts;
    }

    public Order() {}

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Set<OrderProducts> getOrderProducts() {
        return orderProducts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderProducts(Set<OrderProducts> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                totalPrice.equals(order.totalPrice) &&
                Objects.equals(creationDate, order.creationDate) &&
                status == order.status &&
                user.equals(order.user) &&
                orderProducts.equals(order.orderProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, creationDate, status, user, orderProducts);
    }

    public static Order.OrderBuilder builder() {
        return new Order.OrderBuilder();
    }


    public static class OrderBuilder {
        private Long id;
        private BigDecimal totalPrice;
        private LocalDateTime creationDate;
        private Status status;
        private User user;
        private Set<OrderProducts> orderProducts = new HashSet<>();

        OrderBuilder() {
        }

        public Order.OrderBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public Order.OrderBuilder totalPrice(final BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order.OrderBuilder creationDate(final LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Order.OrderBuilder status(final Status status) {
            this.status = status;
            return this;
        }

        public Order.OrderBuilder user(final User user) {
            this.user = user;
            return this;
        }

        public Order.OrderBuilder orderProducts(final Set<OrderProducts> orderProducts) {
            this.orderProducts = orderProducts;
            return this;
        }

        public Order build() {
            return new Order(this.id, this.totalPrice, this.creationDate, this.status, this.user, this.orderProducts);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", createDate=" + creationDate +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
