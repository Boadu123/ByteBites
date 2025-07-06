package com.example.restaurant_service.dto.kafkaMessageDTO;

import jakarta.validation.constraints.NotNull;
public class OrderRequestDTO {
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Restaurant ID is required")
    private Long restaurantId;

    @NotNull(message = "Menu item ID is required")
    private Long menuItemId;

    // Default constructor
    public OrderRequestDTO() {}

    // All-args constructor
    public OrderRequestDTO(Long customerId, Long restaurantId, Long menuItemId) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.menuItemId = menuItemId;
    }

    // Getters and setters
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

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }
}