package com.example.order_service.mapper;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.models.Order;

public class OrderMapper {

    public static Order toEntity(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO == null) {
            return null;
        }

        return Order.builder()
                .customerId(orderRequestDTO.getCustomerId())
                .restaurantId(orderRequestDTO.getRestaurantId())
                .menuItems(String.valueOf(orderRequestDTO.getMenuItemId()))
                .build();
    }

    public static OrderResponseDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponseDTO(
                order.getId(),
                order.getCustomerId(),
                order.getRestaurantId(),
                order.getStatus(),
                order.getMenuItemId(),
                order.getCreatedAt()
        );
    }
}