package com.example.restaurant_service.controller;

import com.example.restaurant_service.dto.request.RestaurantRequestDTO;
import com.example.restaurant_service.dto.response.RestaurantResponseDTO;
import com.example.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.restaurant_service.utils.SucessResponseUtil.sucessResponseUtil;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /** Create a new restaurant */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createRestaurant(@Valid @RequestBody RestaurantRequestDTO requestDTO) {
        RestaurantResponseDTO response = restaurantService.createRestaurant(requestDTO);
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
    public ResponseEntity<Map<String, Object>> updateRestaurant(@PathVariable Long id,
                                                                @Valid @RequestBody RestaurantRequestDTO requestDTO) {
        RestaurantResponseDTO response = restaurantService.updateRestaurant(id, requestDTO);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Delete a restaurant */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);

        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.OK.value());
        response.put("status", "success");
        response.put("message", "Restaurant deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
