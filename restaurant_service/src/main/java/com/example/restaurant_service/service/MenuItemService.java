package com.example.restaurant_service.service;

import com.example.restaurant_service.dto.request.MenuItemRequestDTO;
import com.example.restaurant_service.dto.response.MenuItemResponseDTO;
import com.example.restaurant_service.exception.MenuItemNotFoundException;
import com.example.restaurant_service.exception.RestaurantNotFoundException;
import com.example.restaurant_service.mapper.MenuItemMapper;
import com.example.restaurant_service.models.Menu;
import com.example.restaurant_service.models.Restaurant;
import com.example.restaurant_service.repository.MenuItemRepository;
import com.example.restaurant_service.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService implements MenuItemServiceInterface {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository,
                           RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItemResponseDTO createMenuItem(MenuItemRequestDTO requestDTO) {
        Restaurant restaurant = restaurantRepository.findById(requestDTO.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(
                        "Restaurant not found with id: " + requestDTO.getRestaurantId())
                );

        Menu menuItem = MenuItemMapper.toEntity(requestDTO);
        menuItem.setRestaurant(restaurant);

        Menu savedItem = menuItemRepository.save(menuItem);
        return MenuItemMapper.toDTO(savedItem);
    }

    public List<MenuItemResponseDTO> getMenuItemsByRestaurant(Long restaurantId) {
        List<Menu> menuItems = menuItemRepository.findByRestaurantId(restaurantId);
        return menuItems.stream()
                .map(MenuItemMapper::toDTO)
                .toList();
    }

    public MenuItemResponseDTO getMenuItemById(Long menuItemId) {
        Menu menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new MenuItemNotFoundException(
                        "Menu item not found with id: " + menuItemId)
                );
        return MenuItemMapper.toDTO(menuItem);
    }

    public MenuItemResponseDTO updateMenuItem(Long menuItemId, MenuItemRequestDTO requestDTO) {
        Menu existingItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new MenuItemNotFoundException(
                        "Menu item not found with id: " + menuItemId)
                );

        // Update restaurant if it has changed
        if (!existingItem.getRestaurant().getId().equals(requestDTO.getRestaurantId())) {
            Restaurant newRestaurant = restaurantRepository.findById(requestDTO.getRestaurantId())
                    .orElseThrow(() -> new RestaurantNotFoundException(
                            "Restaurant not found with id: " + requestDTO.getRestaurantId())
                    );
            existingItem.setRestaurant(newRestaurant);
        }

        // Update fields
        existingItem.setName(requestDTO.getName());
        existingItem.setPrice(requestDTO.getPrice());
        existingItem.setDescription(requestDTO.getDescription());

        Menu updatedItem = menuItemRepository.save(existingItem);
        return MenuItemMapper.toDTO(updatedItem);
    }

    public void deleteMenuItem(Long menuItemId) {
        if (!menuItemRepository.existsById(menuItemId)) {
            throw new MenuItemNotFoundException("Menu item not found with id: " + menuItemId);
        }
        menuItemRepository.deleteById(menuItemId);
    }
}
