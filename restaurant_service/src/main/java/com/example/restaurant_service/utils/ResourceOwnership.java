package com.example.restaurant_service.utils;


import com.example.restaurant_service.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

@Component("resourceOwner")
public class ResourceOwnership {
    private final RestaurantRepository restaurantRepository;

    public ResourceOwnership(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean isOrderOwner(long restaurantId, long userId) {
        System.out.println("Restaurant Id " + restaurantId);
        System.out.println("User Id " + userId);
        return restaurantRepository.findById(restaurantId)
                .map(order -> order.getOwnerId() == userId)
                .orElse(false);
    }
}
