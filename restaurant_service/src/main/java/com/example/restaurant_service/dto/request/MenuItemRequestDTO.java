package com.example.restaurant_service.dto.request;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class MenuItemRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Long restaurantId;

    // Default constructor
    public MenuItemRequestDTO() {}

    // All-args constructor
    public MenuItemRequestDTO(String name, BigDecimal price, String description, Long restaurantId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.restaurantId = restaurantId;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
