package com.example.order_service.utils;

import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component("resourceOwner")
public class ResourceOwnership {
    private final OrderRepository orderRepository;

    public ResourceOwnership(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean isOrderOwner(long orderId, long userId) {
        return orderRepository.findById(orderId)
                .map(order -> order.getCustomerId() == userId)
                .orElse(false);
    }
}
