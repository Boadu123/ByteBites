package com.example.order_service;

import com.example.order_service.controller.OrderController;
import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.security.JwtService;
import com.example.order_service.service.OrderService;
import com.example.order_service.utils.ResourceOwnership;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ResourceOwnership resourceOwnership;

    @MockBean
    private JwtService jwtService; // Added to fix the dependency injection issue

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void createOrder_ShouldReturnCreated_WhenValidRequest() throws Exception {
        OrderRequestDTO request = new OrderRequestDTO(1L, 2L, 3L);
        OrderResponseDTO response = new OrderResponseDTO(1L, 1L, 2L, OrderStatus.PENDING, 3L, new Date());

        Mockito.when(orderService.createOrder(any(OrderRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/orders")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is(201)))
                .andExpect(jsonPath("$.message", is("success")))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.customerId", is(1)));
    }



    @Test
    @WithMockUser(roles = "CUSTOMER")
    void getAllOrders_ShouldReturnOrders_WhenAuthorized() throws Exception {
        OrderResponseDTO order1 = new OrderResponseDTO(1L, 1L, 2L, OrderStatus.PENDING, 3L, new Date());
        OrderResponseDTO order2 = new OrderResponseDTO(2L, 1L, 2L, OrderStatus.CONFIRMED, 4L, new Date());
        List<OrderResponseDTO> orders = Arrays.asList(order1, order2);

        Mockito.when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.message", is("success")))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[1].id", is(2)));
    }

    @Test
    @WithMockUser(username = "owner", roles = "CUSTOMER")
    void getOrderById_ShouldReturnOrder_WhenOwner() throws Exception {
        OrderResponseDTO response = new OrderResponseDTO(1L, 1L, 2L, OrderStatus.PENDING, 3L, new Date());

        Mockito.when(orderService.getOrderById(1L)).thenReturn(response);
        Mockito.when(resourceOwnership.isOrderOwner(1L, 42L)).thenReturn(true);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.message", is("success")))
                .andExpect(jsonPath("$.data.id", is(1)));
    }

    @Test
    void getAllOrders_ShouldReturnUnauthorized_WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isUnauthorized());
    }
}