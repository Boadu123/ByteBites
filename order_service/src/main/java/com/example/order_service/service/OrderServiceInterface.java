package com.example.order_service.service;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;

import java.util.List;

public interface OrderServiceInterface {

    OrderResponseDTO createOrder(OrderRequestDTO requestDTO);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO getOrderById(Long orderId);

//    List<OrderResponseDTO> getOrdersByRestaurant(Long restaurantId);
//
//    OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status);
}
