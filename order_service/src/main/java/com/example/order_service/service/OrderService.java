package com.example.order_service.service;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.models.Order;
import com.example.order_service.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderRequestDTO> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, OrderRequestDTO> kafkaTemplate) {

        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Order request cannot be null");
        }

        Order order = OrderMapper.toEntity(requestDTO);
        Order saved = orderRepository.save(order);
        kafkaTemplate.send("order_create", requestDTO);
        System.out.println("Order created successfully");
        return OrderMapper.toDTO(saved);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        return OrderMapper.toDTO(order);
    }

//    public List<OrderResponseDTO> getOrdersByRestaurant(Long restaurantId) {
//        return orderRepository.findByRestaurantId(restaurantId).stream()
//                .map(OrderMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
//        order.setStatus(status);
//        Order updated = orderRepository.save(order);
//
//        return OrderMapper.toDTO(updated);
//    }
}
