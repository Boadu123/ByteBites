package com.example.order_service.controller;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.example.order_service.utils.SucessResponseUtil.sucessResponseUtil;

@RestController
@RequestMapping("/api/orders")
@PreAuthorize("hasRole('CUSTOMER')")
public class OrderController {

    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /** Creates a new order. */
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Map<String, Object>> createOrder(@Valid @RequestBody OrderRequestDTO requestDTO) {
        logger.info("📦 Received order creation request - userId={}, items={}", requestDTO.getCustomerId(), requestDTO.getMenuItemId());

        OrderResponseDTO response = orderService.createOrder(requestDTO);

        logger.info("Order successfully created - orderId={}, userId={}", response.getId(), requestDTO.getCustomerId());
        return sucessResponseUtil(HttpStatus.CREATED, response);
    }

    /** Retrieves all orders. */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {
        logger.info(" Fetching all orders");

        List<OrderResponseDTO> response = orderService.getAllOrders();

        logger.info(" Retrieved {} orders", response.size());
        return sucessResponseUtil(HttpStatus.OK, response);
    }

    /** Retrieves a specific order by ID. */
    @GetMapping("/{id}")
    @PreAuthorize("@resourceOwner.isOrderOwner(#id, authentication.getPrincipal())")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable Long id) {
        logger.info("🔍 Request to fetch order by id={}", id);

        OrderResponseDTO response = orderService.getOrderById(id);

        logger.info(" Fetched order details - orderId={}", id);
        return sucessResponseUtil(HttpStatus.OK, response);
    }

}
