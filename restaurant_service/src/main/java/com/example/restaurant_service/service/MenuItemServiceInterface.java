package com.example.restaurant_service.service;

import com.example.restaurant_service.dto.request.MenuItemRequestDTO;
import com.example.restaurant_service.dto.response.MenuItemResponseDTO;

import java.util.List;

public interface MenuItemServiceInterface {

    MenuItemResponseDTO createMenuItem(MenuItemRequestDTO requestDTO);

    List<MenuItemResponseDTO> getMenuItemsByRestaurant(Long restaurantId);

    MenuItemResponseDTO getMenuItemById(Long menuItemId);

    MenuItemResponseDTO updateMenuItem(Long menuItemId, MenuItemRequestDTO requestDTO);

    void deleteMenuItem(Long menuItemId);
}
