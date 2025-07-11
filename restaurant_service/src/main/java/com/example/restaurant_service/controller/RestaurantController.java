package com.example.restaurant_service.controller;

import com.example.restaurant_service.dto.request.RestaurantRequestDTO;
import com.example.restaurant_service.dto.response.RestaurantResponseDTO;
import com.example.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /** Create a new restaurant */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createRestaurant(@Valid @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        logger.info("Received request to create restaurant for ownerId={} with name={}",
                restaurantRequestDTO.getOwnerId(), restaurantRequestDTO.getName());

        RestaurantResponseDTO response = restaurantService.createRestaurant(restaurantRequestDTO);

        logger.info("Restaurant created - restaurantId={}, ownerId={}",
                response.getId(), response.getOwnerId());
        return sucessResponseUtil(HttpStatus.CREATED, response);
    }

    /** Get all restaurants */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRestaurants() {
        logger.info("Request to fetch all restaurants");

        List<RestaurantResponseDTO> response = restaurantService.getAllRestaurants();

        logger.info("Retrieved {} restaurants", response.size());
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Get a single restaurant by ID */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRestaurantById(@PathVariable Long id) {
        logger.info("Request to fetch restaurant by id={}", id);

        RestaurantResponseDTO response = restaurantService.getRestaurantById(id);

        logger.info("Restaurant fetched - id={}", id);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Update a restaurant */
    @PutMapping("/{id}")
    @PreAuthorize("@resourceOwner.isOrderOwner(#id, authentication.getPrincipal())")
    public ResponseEntity<Map<String, Object>> updateRestaurant(@PathVariable Long id,
                                                                @Valid @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
        logger.info("✏️ Request to update restaurant - id={}, newName={}", id, restaurantRequestDTO.getName());

        RestaurantResponseDTO response = restaurantService.updateRestaurant(id, restaurantRequestDTO);

        logger.info("Restaurant updated - id={}, updatedName={}", id, response.getName());
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Delete a restaurant */
    @DeleteMapping("/{id}")
    @PreAuthorize("@resourceOwner.isOrderOwner(#id, authentication.getPrincipal())")
    public ResponseEntity<Map<String, Object>> deleteRestaurant(@PathVariable Long id) {
        logger.info("🗑Request to delete restaurant - id={}", id);

        restaurantService.deleteRestaurant(id);

        logger.info("Restaurant deleted - id={}", id);

        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.OK.value());
        response.put("status", "success");
        response.put("message", "Restaurant deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}