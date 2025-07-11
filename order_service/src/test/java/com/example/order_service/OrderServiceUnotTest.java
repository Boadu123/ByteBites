package com.example.order_service;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.models.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnotTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private KafkaTemplate<String, OrderRequestDTO> kafkaTemplate;

	@InjectMocks
	private OrderService orderService;

	private OrderRequestDTO requestDTO;
	private Order orderEntity;
	private OrderResponseDTO responseDTO;

	@BeforeEach
	void setUp() {
		requestDTO = new OrderRequestDTO(1L, 2L, 3L);

		orderEntity = new Order();
		orderEntity.setId(1L);
		orderEntity.setCustomerId(1L);
		orderEntity.setRestaurantId(2L);
		orderEntity.setMenuItemId(3L);
		orderEntity.setStatus(OrderStatus.PENDING);
		orderEntity.setCreatedAt(new Date());

		responseDTO = new OrderResponseDTO(1L, 1L, 2L, OrderStatus.PENDING, 3L, orderEntity.getCreatedAt());
	}


	@Test
	void testCreateOrder_Success() {
		try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {
			mapper.when(() -> OrderMapper.toEntity(requestDTO)).thenReturn(orderEntity);
			when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
			mapper.when(() -> OrderMapper.toDTO(orderEntity)).thenReturn(responseDTO);

			OrderResponseDTO result = orderService.createOrder(requestDTO);

			assertNotNull(result);
			assertEquals(1L, result.getId());
			verify(orderRepository).save(orderEntity);
			verify(kafkaTemplate).send("order_create", requestDTO);
		}
	}


	@Test
	void testCreateOrder_RepositoryFailure() {
		try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {
			mapper.when(() -> OrderMapper.toEntity(requestDTO)).thenReturn(orderEntity);
			when(orderRepository.save(orderEntity)).thenThrow(new RuntimeException("DB down"));

			RuntimeException ex = assertThrows(RuntimeException.class, () -> orderService.createOrder(requestDTO));
			assertEquals("DB down", ex.getMessage());
			verify(orderRepository).save(orderEntity);
			verify(kafkaTemplate, never()).send(anyString(), any());
		}
	}

	@Test
	void testCreateOrder_KafkaFailure() {
		try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {
			mapper.when(() -> OrderMapper.toEntity(requestDTO)).thenReturn(orderEntity);
			when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
			doThrow(new RuntimeException("Kafka not available")).when(kafkaTemplate).send("order_create", requestDTO);

			assertThrows(RuntimeException.class, () -> orderService.createOrder(requestDTO));
			verify(orderRepository).save(orderEntity);
			verify(kafkaTemplate).send("order_create", requestDTO);
		}
	}

	@Test
	void testCreateOrder_NullInput() {
		assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(null));
	}

	@Test
	void testGetAllOrders_Success() {
		try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {
			when(orderRepository.findAll()).thenReturn(Collections.singletonList(orderEntity));
			mapper.when(() -> OrderMapper.toDTO(orderEntity)).thenReturn(responseDTO);

			List<OrderResponseDTO> result = orderService.getAllOrders();

			assertEquals(1, result.size());
			assertEquals(1L, result.get(0).getId());
			verify(orderRepository).findAll();
		}
	}

	@Test
	void testGetAllOrders_RepositoryFailure() {
		when(orderRepository.findAll()).thenThrow(new RuntimeException("Database failure"));

		RuntimeException ex = assertThrows(RuntimeException.class, () -> orderService.getAllOrders());
		assertEquals("Database failure", ex.getMessage());
		verify(orderRepository).findAll();
	}

	@Test
	void testGetOrderById_Success() {
		try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {
			when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
			mapper.when(() -> OrderMapper.toDTO(orderEntity)).thenReturn(responseDTO);

			OrderResponseDTO result = orderService.getOrderById(1L);

			assertNotNull(result);
			assertEquals(1L, result.getId());
			verify(orderRepository).findById(1L);
		}
	}

	@Test
	void testGetOrderById_NotFound() {
		when(orderRepository.findById(99L)).thenReturn(Optional.empty());

		OrderNotFoundException ex = assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(99L));
		assertEquals("Order not found with id: 99", ex.getMessage());
		verify(orderRepository).findById(99L);
	}

	@Test
	void testGetOrderById_RepositoryFailure() {
		when(orderRepository.findById(1L)).thenThrow(new RuntimeException("Repo error"));

		RuntimeException ex = assertThrows(RuntimeException.class, () -> orderService.getOrderById(1L));
		assertEquals("Repo error", ex.getMessage());
		verify(orderRepository).findById(1L);
	}
}
