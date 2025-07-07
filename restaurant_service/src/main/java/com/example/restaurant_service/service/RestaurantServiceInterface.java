package com.example.restaurant_service.service;

import com.example.restaurant_service.dto.kafkaMessageDTO.OrderRequestDTO;
import com.example.restaurant_service.dto.request.RestaurantRequestDTO;
import com.example.restaurant_service.dto.response.RestaurantResponseDTO;

import java.util.List;

public interface RestaurantServiceInterface {

    RestaurantResponseDTO createRestaurant(RestaurantRequestDTO requestDTO);

    List<RestaurantResponseDTO> getAllRestaurants();

    RestaurantResponseDTO getRestaurantById(Long restaurantId);

    RestaurantResponseDTO updateRestaurant(Long restaurantId, RestaurantRequestDTO requestDTO);

    void deleteRestaurant(Long restaurantId);

    void listenOrderCreated(OrderRequestDTO orderRequestDTO);
}
