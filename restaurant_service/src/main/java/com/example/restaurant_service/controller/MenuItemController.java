package com.example.restaurant_service.controller;

import com.example.restaurant_service.dto.request.MenuItemRequestDTO;
import com.example.restaurant_service.dto.response.MenuItemResponseDTO;
import com.example.restaurant_service.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.restaurant_service.utils.SucessResponseUtil.sucessResponseUtil;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    /** Create a new menu item */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMenuItem(@Valid @RequestBody MenuItemRequestDTO requestDTO) {
        MenuItemResponseDTO response = menuItemService.createMenuItem(requestDTO);
        return sucessResponseUtil(HttpStatus.CREATED, response);
    }

    /** Get all menu items by restaurant ID */
    @GetMapping("/by-restaurant/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        List<MenuItemResponseDTO> response = menuItemService.getMenuItemsByRestaurant(restaurantId);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Get menu item by ID */
    @GetMapping("/{menuItemId}")
    public ResponseEntity<Map<String, Object>> getMenuItemById(@PathVariable Long menuItemId) {
        MenuItemResponseDTO response = menuItemService.getMenuItemById(menuItemId);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Update menu item */
    @PutMapping("/{menuItemId}")
    public ResponseEntity<Map<String, Object>> updateMenuItem(
            @PathVariable Long menuItemId,
            @Valid @RequestBody MenuItemRequestDTO requestDTO) {
        MenuItemResponseDTO response = menuItemService.updateMenuItem(menuItemId, requestDTO);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Delete menu item */
    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<Map<String, Object>> deleteMenuItem(@PathVariable Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);

        return new ResponseEntity<>(Map.of(
                "statusCode", HttpStatus.OK.value(),
                "status", "success",
                "message", "Menu item deleted successfully"
        ), HttpStatus.OK);
    }
}
