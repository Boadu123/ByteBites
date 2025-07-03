package com.example.restaurant_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RestaurantRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

    public RestaurantRequestDTO() {}

    public RestaurantRequestDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
