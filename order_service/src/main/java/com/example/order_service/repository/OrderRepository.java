package com.example.order_service.repository;

import com.example.order_service.enums.OrderStatus;
import com.example.order_service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find all orders by customer ID
    List<Order> findByCustomerId(Long customerId);

    // Find all orders by restaurant ID
    List<Order> findByRestaurantId(Long restaurantId);

    // Find orders by status
    List<Order> findByStatus(OrderStatus status);

    // Find orders by customer ID and status
    List<Order> findByCustomerIdAndStatus(Long customerId, OrderStatus status);

    // Find orders by restaurant ID and status
    List<Order> findByRestaurantIdAndStatus(Long restaurantId, OrderStatus status);

    // Find orders created between two dates
    List<Order> findByCreatedAtBetween(Date startDate, Date endDate);
}