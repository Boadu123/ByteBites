package com.example.order_service.exception;

public class RestaurantServiceException extends RuntimeException {
    public RestaurantServiceException(String message) {
        super(message);
    }
}
