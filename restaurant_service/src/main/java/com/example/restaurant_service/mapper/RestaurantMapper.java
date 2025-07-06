package com.example.restaurant_service.mapper;

import com.example.restaurant_service.dto.request.RestaurantRequestDTO;
import com.example.restaurant_service.dto.response.RestaurantResponseDTO;
import com.example.restaurant_service.models.Restaurant;

import java.util.Collections;
import java.util.stream.Collectors;

public class RestaurantMapper {

    public static Restaurant toEntity(RestaurantRequestDTO dto) {
        if (dto == null) return null;

        return Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .ownerId(dto.getOwnerId())
                .build();
    }

    public static RestaurantResponseDTO toDTO(Restaurant restaurant) {
        if (restaurant == null) return null;

        return new RestaurantResponseDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getOwnerId(),
                restaurant.getMenuItems() != null
                        ? restaurant.getMenuItems().stream()
                        .map(MenuItemMapper::toDTO)
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );
    }
}
