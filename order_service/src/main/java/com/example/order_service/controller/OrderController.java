package com.example.order_service.controller;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.service.OrderService;
import com.example.order_service.utils.AccessChecker;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.order_service.utils.SucessResponseUtil.sucessResponseUtil;

@RestController
@RequestMapping("/api/orders")
@PreAuthorize("hasRole('CUSTOMER')")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /** Creates a new order. */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@Valid @RequestBody OrderRequestDTO requestDTO) {
        System.out.println(" Is the error here ");
        OrderResponseDTO response = orderService.createOrder(requestDTO);
        return sucessResponseUtil(HttpStatus.CREATED, response);
    }

    /** Retrieves all orders. */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {
        List<OrderResponseDTO> response = orderService.getAllOrders();
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Retrieves a specific order by ID. */
    @GetMapping("/{id}")
    @PreAuthorize("@resourceOwner.isOrderOwner(#id, authentication.getPrincipal())")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable Long id) {
        OrderResponseDTO response = orderService.getOrderById(id);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Retrieves orders by restaurant ID. */
    @GetMapping("/by-restaurant/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        List<OrderResponseDTO> response = orderService.getOrdersByRestaurant(restaurantId);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Updates the status of an order. */
    @PutMapping("/{id}/status")
    @PreAuthorize("@resourceOwner.isOrderOwner(#id, authentication.getPrincipal())")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(@PathVariable Long id,
                                                                 @RequestParam OrderStatus status) {
        OrderResponseDTO response = orderService.updateOrderStatus(id, status);
        return sucessResponseUtil(HttpStatus.OK, response);
    }
}
