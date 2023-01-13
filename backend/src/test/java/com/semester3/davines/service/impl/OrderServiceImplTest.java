package com.semester3.davines.service.impl;

import com.semester3.davines.domain.models.*;
import com.semester3.davines.domain.requests.CreateOrderRequest;
import com.semester3.davines.domain.requests.UpdateOrderStatus;
import com.semester3.davines.domain.response.CreateOrderResponse;
import com.semester3.davines.domain.response.GetAllOrdersResponse;
import com.semester3.davines.repository.OrderProductsRepository;
import com.semester3.davines.repository.OrderRepository;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.UserRepository;
import com.semester3.davines.repository.entity.*;
import com.semester3.davines.repository.entity.enums.OrderStatusEnum;
import com.semester3.davines.repository.entity.enums.UserRoleEnum;
import com.semester3.davines.service.exception.InvalidUserIdException;
import com.semester3.davines.service.exception.UnauthorizedDataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderProductsRepository orderProductsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private AccessToken requestAccessToken;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderEntity order1, order2;
    private ProductEntity product;
    private OrderProductsEntity orderProducts;
    private PageRequest pageRequest;
    private Page<OrderEntity> ordersPagable;
    private UserEntity user;

    @BeforeEach
    void setUp() {
        SeriesEntity series;

        series = SeriesEntity.builder()
                .name("Davines")
                .description("description")
                .image("image")
                .build();
        series.setId(1L);

        product = ProductEntity.builder()
                .name("Love")
                .description("description")
                .quantity(10L)
                .price(10.0)
                .series(series)
                .image("image")
                .type("shampoo")
                .build();
        product.setId(1L);

        orderProducts = OrderProductsEntity.builder()
                .product(product)
                .quantity(1L)
                .build();
        orderProducts.setId(1L);

        order1 = OrderEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .country("Romania")
                .city("Bucharest")
                .address("Str. 1")
                .phone("123456789")
                .orderDate("2021-05-01")
                .orderStatus(OrderStatusEnum.PENDING)
                .products(List.of(orderProducts))
                .totalPrice(100.0)
                .build();
        order1.setId(1L);

        user = UserEntity.builder()
                .email("test@gmail.com")
                .phoneNumber("123456789")
                .firstName("Test")
                .lastName("Test")
                .birthday("12/12/2000")
                .userRoles(Set.of(
                        UserRoleEntity.builder()
                                .role(UserRoleEnum.USER)
                                .build()))
                .password("password")
                .build();
        user.setId(1L);

        order2 = OrderEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .country("Romania")
                .city("Bucharest")
                .address("Str. 1")
                .phone("123456789")
                .orderDate("2021-05-01")
                .orderStatus(OrderStatusEnum.IN_PROGRESS)
                .products(List.of(orderProducts))
                .totalPrice(100.0)
                .build();
        order2.setId(2L);

        pageRequest = PageRequest.of(0, 4);
        ordersPagable = new PageImpl<>(List.of(order1), pageRequest, 4);
    }

    @Test
    void getAllOrders() {
        when(orderRepository.findAll(pageRequest))
                .thenReturn(ordersPagable);

        Page<Order> pageOrders = orderService.getAllOrders(0);

        assertEquals(ordersPagable.getSize(), pageOrders.getSize());
        verify(orderRepository, times(1)).findAll(pageRequest);
    }

    @Test
    void getAllOrdersByUserEmail() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(true);
        when(userRepository.findById(any()))
                .thenReturn(Optional.ofNullable(user));
        when(orderRepository.findAllByEmail(pageRequest, user.getEmail()))
                .thenReturn(ordersPagable);

        Page<Order> pageOrders = orderService.getAllOrdersByUserEmail(0, 1L);

        assertEquals(ordersPagable.getSize(), pageOrders.getSize());
        verify(orderRepository, times(1)).findAllByEmail(pageRequest, user.getEmail());
    }

    @Test
    void getAllOrdersByUserEmail_NotAdminButUserItself() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(false);
        when(requestAccessToken.getUserId())
                .thenReturn(1L);
        when(userRepository.findById(any()))
                .thenReturn(Optional.ofNullable(user));
        when(orderRepository.findAllByEmail(pageRequest, user.getEmail()))
                .thenReturn(ordersPagable);

        Page<Order> pageOrders = orderService.getAllOrdersByUserEmail(0, 1L);

        assertEquals(ordersPagable.getSize(), pageOrders.getSize());
        verify(orderRepository, times(1)).findAllByEmail(pageRequest, user.getEmail());
    }

    @Test
    void getAllOrdersByUserEmail_ShouldThrowExceptionUnauthorizedDataAccessException() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(false);
        when(requestAccessToken.getUserId())
                .thenReturn(1L);

        assertThrows(UnauthorizedDataAccessException.class, () -> orderService.getAllOrdersByUserEmail(0, 2L));
    }

    @Test
    void getAllOrderByUserEmail_ShouldThrowInvalidUserIdException() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(true);
        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidUserIdException.class, () -> orderService.getAllOrdersByUserEmail(0, 2L));
    }

    @Test
    void getLastThreeOrders() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(true);
        when(userRepository.findById(any()))
                .thenReturn(Optional.ofNullable(user));
        when(orderRepository.findAllByEmail(user.getEmail()))
                .thenReturn(List.of(order1));

        GetAllOrdersResponse response = orderService.getLastThreeOrders(1L);

        assertEquals(1, response.getOrders().size());
        verify(orderRepository, times(1)).findAllByEmail(user.getEmail());
        verify(userRepository, times(2)).findById(any());
    }

    @Test
    void getLastThreeOrders_ShouldThrowExceptionUnauthorizedDataAccessException() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(false);
        when(requestAccessToken.getUserId())
                .thenReturn(1L);

        assertThrows(UnauthorizedDataAccessException.class, () -> orderService.getLastThreeOrders(2L));
    }

    @Test
    void getLastThreeOrders_ShouldThrowInvalidUserIdException() {
        when(requestAccessToken.hasRole(UserRoleEnum.ADMIN.name()))
                .thenReturn(true);
        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidUserIdException.class, () -> orderService.getLastThreeOrders(2L));
    }

    @Test
    void updateProductQuantity() {
        when(orderRepository.findById(1L))
                .thenReturn(Optional.ofNullable(order1));
        when(productRepository.findById(any()))
                .thenReturn(Optional.ofNullable(product));
        when(productRepository.save(any()))
                .thenReturn(product);
        when(orderRepository.save(any()))
                .thenReturn(order1);

        UpdateOrderStatus request = UpdateOrderStatus.builder()
                .status(String.valueOf(OrderStatusEnum.IN_PROGRESS))
                .build();

        orderService.updateProductQuantity(request, 1L);

        verify(orderRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).findById(any());
        verify(productRepository, times(1)).save(any());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void updateProductQuantity_Product() {
        when(orderRepository.findById(2L))
                .thenReturn(Optional.ofNullable(order2));
        when(orderRepository.save(any()))
                .thenReturn(order2);

        UpdateOrderStatus request = UpdateOrderStatus.builder()
                .status(String.valueOf(OrderStatusEnum.DELIVERED))
                .build();

        orderService.updateProductQuantity(request, 2L);

        verify(orderRepository, times(1)).findById(2L);
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void createOrder() {
        when(orderRepository.save(any()))
                .thenReturn(order1);
        when(orderProductsRepository.save(any()))
                .thenReturn(orderProducts);

        CreateOrderRequest expectedRequest = CreateOrderRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .country("Romania")
                .city("Bucharest")
                .address("Str. 1")
                .phone("123456789")
                .orderDate("2021-05-01")
                .products(List.of(ProductConverter.convert(product)))
                .totalPrice(100.0)
                .build();


        CreateOrderResponse actualResponse = orderService.createOrder(expectedRequest);

        assertEquals(1L, actualResponse.getOrderId());
        verify(orderRepository, times(1)).save(any());
        verify(orderProductsRepository, times(1)).save(any());
    }
}