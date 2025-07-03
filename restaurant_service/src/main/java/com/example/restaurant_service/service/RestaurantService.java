package com.example.restaurant_service.service;

import com.example.restaurant_service.dto.request.RestaurantRequestDTO;
import com.example.restaurant_service.dto.response.RestaurantResponseDTO;
import com.example.restaurant_service.exception.RestaurantNotFoundException;
import com.example.restaurant_service.mapper.RestaurantMapper;
import com.example.restaurant_service.models.Restaurant;
import com.example.restaurant_service.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /** Create a new restaurant */
    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO requestDTO) {
        Restaurant restaurant = RestaurantMapper.toEntity(requestDTO);
        Restaurant saved = restaurantRepository.save(restaurant);
        return RestaurantMapper.toDTO(saved);
    }

    /** Get all restaurants */
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantMapper::toDTO)
                .collect(Collectors.toList());
    }

    /** Get a single restaurant by ID */
    public RestaurantResponseDTO getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId));
        return RestaurantMapper.toDTO(restaurant);
    }

    /** Update a restaurant by ID */
    public RestaurantResponseDTO updateRestaurant(Long restaurantId, RestaurantRequestDTO requestDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId));
        restaurant.setName(requestDTO.getName());
        restaurant.setAddress(requestDTO.getAddress());
        Restaurant updated = restaurantRepository.save(restaurant);
        return RestaurantMapper.toDTO(updated);
    }

    /** Delete a restaurant by ID */
    public void deleteRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId);
        }
        restaurantRepository.deleteById(restaurantId);
    }
}
