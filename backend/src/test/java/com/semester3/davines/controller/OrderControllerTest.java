package com.semester3.davines.controller;

import com.semester3.davines.domain.models.Order;
import com.semester3.davines.domain.models.OrderProducts;
import com.semester3.davines.domain.models.Product;
import com.semester3.davines.domain.models.Series;
import com.semester3.davines.domain.requests.CreateOrderRequest;
import com.semester3.davines.domain.requests.UpdateOrderStatus;
import com.semester3.davines.domain.response.CreateOrderResponse;
import com.semester3.davines.domain.response.GetAllOrdersResponse;
import com.semester3.davines.repository.entity.OrderProductsEntity;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.repository.entity.SeriesEntity;
import com.semester3.davines.repository.entity.enums.OrderStatusEnum;
import com.semester3.davines.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private Page<Order> orderPage;
    private PageRequest pageRequest;
    private OrderProducts orderProducts;
    private Order order;

    @BeforeEach
    void setUp() {
        Series series;

        series = Series.builder()
                .name("Davines")
                .description("description")
                .image("image")
                .build();
        series.setId(1L);

        Product product = Product.builder()
                .name("Love")
                .description("description")
                .quantity(10L)
                .price(10.0)
                .series(series)
                .image("image")
                .type("Shampoo")
                .build();
        product.setId(1L);

        orderProducts = OrderProducts.builder()
                .product(product)
                .quantity(2L)
                .build();
        orderProducts.setId(1L);

        order = Order.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .country("Romania")
                .city("Bucharest")
                .address("Str. 1")
                .phone("123456789")
                .orderDate("2021-05-01")
                .status(OrderStatusEnum.PENDING)
                .products(List.of(orderProducts))
                .total(100.0)
                .build();
        order.setId(1L);

        pageRequest = PageRequest.of(0, 4);
        orderPage = new PageImpl<>(List.of(order), pageRequest, 4);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllOrders() throws Exception {
        when(orderService.getAllOrders(anyInt()))
                .thenReturn(orderPage);

        mockMvc.perform(get("/orders?orderPage=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                            {
                            "content": [
                                {
                                    "id": 1,
                                    "firstName": "John",
                                    "lastName": "Doe",
                                    "email": "john@gmail.com",
                                    "country": "Romania",
                                    "city": "Bucharest",
                                    "address": "Str. 1",
                                    "phone": "123456789",
                                    "orderDate": "2021-05-01",
                                    "status": "PENDING",
                                    "total": 100.0,
                                    "products": [
                                        {
                                            "id": 1,
                                            "product": {
                                                "id": 1,
                                                "name": "Love",
                                                "description": "description",
                                                "type": "Shampoo",
                                                "price": 10.0,
                                                "quantity": 10,
                                                "image": "image",
                                                "series": {
                                                    "id": 1,
                                                    "name": "Davines",
                                                    "description": "description",
                                                    "image": "image"
                                                }
                                            },
                                            "quantity": 2
                                        }
                                    ]
                                }
                            ],
                            "pageable": {
                                "sort": {
                                    "empty": true,
                                    "sorted": false,
                                    "unsorted": true
                                },
                                "offset": 0,
                                "pageSize": 4,
                                "pageNumber": 0,
                                "paged": true,
                                "unpaged": false
                            },
                            "last": true,
                            "totalElements": 4,
                            "totalPages": 1,
                            "size": 4,
                            "number": 0,
                            "sort": {
                                "empty": true,
                                "sorted": false,
                                "unsorted": true
                            },
                            "first": true,
                            "numberOfElements": 1,
                            "empty": false
                        }
                        """));

        verify(orderService).getAllOrders(anyInt());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createOrder() throws Exception {
        CreateOrderRequest request = CreateOrderRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .country("Romania")
                .city("Bucharest")
                .address("Str. 1")
                .phone("123456789")
                .orderDate("2021-05-01")
                .products(List.of(orderProducts.getProduct()))
                .totalPrice(100.0)
                .build();

        when(orderService.createOrder(request))
                .thenReturn(CreateOrderResponse.builder()
                        .orderId(order.getId())
                        .build());

        mockMvc.perform(post("/orders/create")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content("""
                                {
                                    "email": "john@gmail.com",
                                    "firstName": "John",
                                    "lastName": "Doe",
                                    "country": "Romania",
                                    "city": "Bucharest",
                                    "address": "Str. 1",
                                    "phone": "123456789",
                                    "orderDate": "2021-05-01",
                                    "products": [
                                        {
                                            "id": 1,
                                            "name": "Love",
                                            "description": "description",
                                            "type": "Shampoo",
                                            "price": 10.0,
                                            "quantity": 10,
                                            "image": "image",
                                            "series": {
                                                "id": 1,
                                                "name": "Davines",
                                                "description": "description",
                                                "image": "image"
                                            }
                                        }
                                    ],
                                    "totalPrice": 100.0                              }
                                """))
                .andDo(print())
                .andExpect(status().isOk());

        verify(orderService).createOrder(request);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllOrdersByEmail() throws Exception {
        when(orderService.getAllOrdersByUserEmail(0, 1L))
                .thenReturn(orderPage);

        mockMvc.perform(get("/orders/user/1?orderPage=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                            {
                            "content": [
                                {
                                    "id": 1,
                                    "firstName": "John",
                                    "lastName": "Doe",
                                    "email": "john@gmail.com",
                                    "country": "Romania",
                                    "city": "Bucharest",
                                    "address": "Str. 1",
                                    "phone": "123456789",
                                    "orderDate": "2021-05-01",
                                    "status": "PENDING",
                                    "total": 100.0,
                                    "products": [
                                        {
                                            "id": 1,
                                            "product": {
                                                "id": 1,
                                                "name": "Love",
                                                "description": "description",
                                                "type": "Shampoo",
                                                "price": 10.0,
                                                "quantity": 10,
                                                "image": "image",
                                                "series": {
                                                    "id": 1,
                                                    "name": "Davines",
                                                    "description": "description",
                                                    "image": "image"
                                                }
                                            },
                                            "quantity": 2
                                        }
                                    ]
                                }
                            ],
                            "pageable": {
                                "sort": {
                                    "empty": true,
                                    "sorted": false,
                                    "unsorted": true
                                },
                                "offset": 0,
                                "pageSize": 4,
                                "pageNumber": 0,
                                "paged": true,
                                "unpaged": false
                            },
                            "last": true,
                            "totalElements": 4,
                            "totalPages": 1,
                            "size": 4,
                            "number": 0,
                            "sort": {
                                "empty": true,
                                "sorted": false,
                                "unsorted": true
                            },
                            "first": true,
                            "numberOfElements": 1,
                            "empty": false
                        }
                        """));

        verify(orderService).getAllOrdersByUserEmail(0, 1L);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getLastThreeOrders() throws Exception {
        when(orderService.getLastThreeOrders(1L))
                .thenReturn(GetAllOrdersResponse.builder()
                        .orders(List.of(order))
                        .build());

        mockMvc.perform(get("/orders/user/last/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                            {
                            "orders": [
                                {
                                    "id": 1,
                                    "firstName": "John",
                                    "lastName": "Doe",
                                    "email": "john@gmail.com",
                                    "country": "Romania",
                                    "city": "Bucharest",
                                    "address": "Str. 1",
                                    "phone": "123456789",
                                    "orderDate": "2021-05-01",
                                    "status": "PENDING",
                                    "total": 100.0,
                                    "products": [
                                        {
                                            "id": 1,
                                            "product": {
                                                "id": 1,
                                                "name": "Love",
                                                "description": "description",
                                                "type": "Shampoo",
                                                "price": 10.0,
                                                "quantity": 10,
                                                "image": "image",
                                                "series": {
                                                    "id": 1,
                                                    "name": "Davines",
                                                    "description": "description",
                                                    "image": "image"
                                                }
                                            },
                                            "quantity": 2
                                        }
                                    ]
                                }
                            ]
                        }
                        """));

        verify(orderService).getLastThreeOrders(1L);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updateStatusAndQuantity() throws Exception {
        mockMvc.perform(put("/orders/update/1")
                        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content("""
                                {
                                    "id": 1,
                                    "status": "DELIVERED",
                                    "products": [
                                        {
                                            "id": 1,
                                            "quantity": 2
                                        }
                                    ]
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk());

        verify(orderService).updateProductQuantity(UpdateOrderStatus.builder()
                .status("DELIVERED")
                .build(), 1L);
    }
}