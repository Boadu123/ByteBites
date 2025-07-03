package com.example.restaurant_service.dto.response;

import java.math.BigDecimal;

public class MenuItemResponseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long restaurantId;

    // Default constructor
    public MenuItemResponseDTO() {}

    // All-args constructor
    public MenuItemResponseDTO(Long id, String name, BigDecimal price, String description, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.restaurantId = restaurantId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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