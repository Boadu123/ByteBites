package com.example.restaurant_service.mapper;

import com.example.restaurant_service.dto.request.MenuItemRequestDTO;
import com.example.restaurant_service.dto.response.MenuItemResponseDTO;
import com.example.restaurant_service.models.Menu;
import com.example.restaurant_service.models.Restaurant;

public class MenuItemMapper {

    public static Menu toEntity(MenuItemRequestDTO menuItemRequestDTO) {
        if (menuItemRequestDTO == null) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        return Menu.builder()
                .name(menuItemRequestDTO.getName())
                .price(menuItemRequestDTO.getPrice())
                .description(menuItemRequestDTO.getDescription())
                .restaurant(restaurant)
                .build();
    }

    public static MenuItemResponseDTO toDTO(Menu menuItem) {
        if (menuItem == null) {
            return null;
        }

        return new MenuItemResponseDTO(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.getDescription(),
                menuItem.getRestaurant().getId()
        );
    }
}