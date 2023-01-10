package com.semester3.davines.service.impl;

import com.semester3.davines.domain.response.GetAllOrdersResponse;
import com.semester3.davines.repository.OrderRepository;
import com.semester3.davines.repository.entity.OrderEntity;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.repository.entity.SeriesEntity;
import com.semester3.davines.repository.entity.enums.OrderStatusEnum;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderEntity order1, order2;
    private ProductEntity product1, product2;

    @BeforeEach
    void setUp() {
        SeriesEntity series = SeriesEntity.builder()
                .name("Davines")
                .description("description")
                .build();

        series.setId(1L);

        product1 = ProductEntity.builder()
                .name("Love")
                .type("shampoo")
                .series(series)
                .build();

        product1.setId(1L);

        product2 = ProductEntity.builder()
                .name("Energize")
                .type("shampoo")
                .series(series)
                .build();

        product2.setId(2L);

        order1 = OrderEntity.builder()
                .firstName("Names")
                .lastName("Here")
                .address("address1")
                .email("email@test.com")
                .orderDate("2021-05-05")
                .totalPrice(100.0)
                .orderStatus(OrderStatusEnum.PENDING)
                .build();

        order1.setId(1L);

        order2 = OrderEntity.builder()
                .firstName("Names")
                .lastName("Here")
                .address("address2")
                .email("test@gmail.com")
                .orderDate("2021-05-05")
                .orderStatus(OrderStatusEnum.IN_PROGRESS)
                .totalPrice(50.0)
                .build();

        order2.setId(2L);
    }

//    @Test
//    void getAllOrders() {
//        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));
//
//        GetAllOrdersResponse response = orderService.getAllOrders();
//
//        assertEquals(2, response.getOrders().size());
//        assertEquals(order1.getId(), response.getOrders().get(0).getId());
//        assertEquals(order2.getId(), response.getOrders().get(1).getId());
//        assertEquals(product1.getId(), response.getOrders().get(0).getProducts().get(0).getId());
//        assertEquals(product2.getId(), response.getOrders().get(0).getProducts().get(1).getId());
//        verify(orderRepository).findAll();
//    }
}