package com.example.restaurant_service.controller;

import com.example.restaurant_service.dto.kafkaMessageDTO.OrderRequestDTO;
import com.example.restaurant_service.dto.request.RestaurantRequestDTO;
import com.example.restaurant_service.dto.response.RestaurantResponseDTO;
import com.example.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.restaurant_service.utils.SucessResponseUtil.sucessResponseUtil;

@RestController
@RequestMapping("/api/restaurants")
@PreAuthorize("hasRole('RESTAURANT_OWNER')")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /** Create a new restaurant */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createRestaurant(@Valid @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        RestaurantResponseDTO response = restaurantService.createRestaurant(restaurantRequestDTO);
        return sucessResponseUtil(HttpStatus.CREATED, response);
    }

    /** Get all restaurants */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRestaurants() {
        List<RestaurantResponseDTO> response = restaurantService.getAllRestaurants();
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Get a single restaurant by ID */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRestaurantById(@PathVariable Long id) {
        RestaurantResponseDTO response = restaurantService.getRestaurantById(id);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Update a restaurant */
    @PutMapping("/{id}")
    @PreAuthorize("@resourceOwner.isOrderOwner(#id, authentication.getPrincipal())")
    public ResponseEntity<Map<String, Object>> updateRestaurant(@PathVariable Long id,
                                                                @Valid @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        RestaurantResponseDTO response = restaurantService.updateRestaurant(id, restaurantRequestDTO);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Delete a restaurant */
    @DeleteMapping("/{id}")
    @PreAuthorize("@resourceOwner.isOrderOwner(#id, authentication.getPrincipal())")
    public ResponseEntity<Map<String, Object>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);

        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.OK.value());
        response.put("status", "success");
        response.put("message", "Restaurant deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
