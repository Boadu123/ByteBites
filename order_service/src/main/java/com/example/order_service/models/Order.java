package com.example.order_service.models;

import com.example.order_service.enums.OrderStatus;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long restaurantId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private Long menuItemId;
    private Date createdAt = new Date();

    // Default constructor required by JPA
    public Order() {}

    // All-args constructor
    public Order(Long id, Long customerId, Long restaurantId, OrderStatus status, Long menuItemId, Date createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.menuItemId = menuItemId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Builder Pattern Implementation
    public static class Builder {
        private Long id;
        private Long customerId;
        private Long restaurantId;
        private OrderStatus status = OrderStatus.PENDING;
        private Long menuItemId;
        private Date createdAt = new Date();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder restaurantId(Long restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder menuItems(Long menuItemId) {
            this.menuItemId = menuItemId;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(this.id);
            order.setCustomerId(this.customerId);
            order.setRestaurantId(this.restaurantId);
            order.setStatus(this.status);
            order.setMenuItemId(this.menuItemId);
            order.setCreatedAt(this.createdAt);
            return order;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}