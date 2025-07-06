package com.example.restaurant_service.dto.response;

import java.util.List;

public class RestaurantResponseDTO {

    private Long id;
    private String name;
    private String address;
    private long ownerId;
    private List<MenuItemResponseDTO> menuItems;

    public RestaurantResponseDTO() {}

    public RestaurantResponseDTO(Long id, String name, String address, long ownerId, List<MenuItemResponseDTO> menuItems) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.menuItems = menuItems;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public List<MenuItemResponseDTO> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemResponseDTO> menuItems) {
        this.menuItems = menuItems;
    }
}
