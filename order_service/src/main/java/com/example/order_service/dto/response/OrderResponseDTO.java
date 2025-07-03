package com.example.order_service.dto.response;

import com.example.order_service.enums.OrderStatus;
import java.util.Date;

public class OrderResponseDTO {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private OrderStatus status;
    private Long menuItemId;
    private Date createdAt;

    // Default constructor
    public OrderResponseDTO() {}

    // All-args constructor
    public OrderResponseDTO(Long id, Long customerId, Long restaurantId,
                            OrderStatus status, Long menuItemId, Date createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.menuItemId = menuItemId;
        this.createdAt = createdAt;
    }

    // Getters and setters
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
}

