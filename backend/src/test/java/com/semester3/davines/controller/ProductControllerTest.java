package com.semester3.davines.controller;

import com.semester3.davines.domain.*;
import com.semester3.davines.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ProductControllerTest {

    //TODO: create aplication property for test

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product lovedProduct;
    private Product energizedProduct;

    @BeforeEach
    void setUp() {
        Series loveSeries = Series.builder()
                .id(1L)
                .name("Love")
                .description("Love series")
                .build();

        Series energizeSeries = Series.builder()
                .id(2L)
                .name("Energize")
                .description("Energize series")
                .build();

        lovedProduct = Product.builder()
                .id(1L)
                .name("Loved")
                .description("Loved product")
                .quantity(10L)
                .price(100.0)
                .type("shampoo")
                .series(loveSeries)
                .build();

        energizedProduct = Product.builder()
                .id(2L)
                .name("Energized")
                .description("Energized product")
                .quantity(10L)
                .price(100.0)
                .type("shampoo")
                .series(energizeSeries)
                .build();
    }

    @Test
    @WithAnonymousUser
    void getAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(GetAllProductsResponse.builder()
                .products(List.of(lovedProduct, energizedProduct))
                .build());

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("" +
                        "{\"products\":[{\"id\":1,\"name\":\"Loved\",\"description\":\"Loved product\",\"quantity\":10,\"price\":100.0,\"series\":{\"id\":1,\"name\":\"Love\",\"description\":\"Love series\"}}," +
                        "{\"id\":2,\"name\":\"Energized\",\"description\":\"Energized product\",\"quantity\":10,\"price\":100.0,\"series\":{\"id\":2,\"name\":\"Energize\",\"description\":\"Energize series\"}}]}"
                ));

        verify(productService).getAllProducts();
    }

    @Test
    @WithMockUser(username = "vartan@gmail.com", roles = {"ADMIN"})
    void createProduct() throws Exception {
        CreateProductRequest expectedRequest = CreateProductRequest.builder()
                .name("test")
                .price(10.0)
                .description("test")
                .quantity(0L)
                .type("test")
                .seriesId(1L)
                .build();

        when(productService.createProduct(expectedRequest))
                .thenReturn(CreateProductResponse.builder()
                        .productId(1L)
                        .build());

        mockMvc.perform(post("/products")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                        .content("""
                                {
                                    "name": "test",
                                    "price": 10.0,
                                    "description": "test",
                                    "quantity": 0,
                                    "type": "test",
                                    "seriesId": 1
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                                {
                                    "productId": 1
                                }
                                """));

        verify(productService).createProduct(expectedRequest);
    }

    @Test
    @WithAnonymousUser
    void testGetAllProducts() throws Exception {
        GetProductsRequest expectedRequest = GetProductsRequest.builder().productType("shampoo").build();

        when(productService.getProducts(expectedRequest)).thenReturn(GetProductsResponse.builder()
                .productList(List.of(lovedProduct, energizedProduct))
                .build());

        mockMvc.perform(get("/products/shampoo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "productList": [
                                {
                                    "id": 1,
                                    "name": "Loved",
                                    "description": "Loved product",
                                    "type": "shampoo",
                                    "price": 100.0,
                                    "quantity": 10,
                                    "series": {
                                        "id": 1,
                                        "name": "Love",
                                        "description": "Love series"
                                    }
                                },
                                {
                                    "id": 2,
                                    "name": "Energized",
                                    "description": "Energized product",
                                    "type": "shampoo",
                                    "price": 100.0,
                                    "quantity": 10,
                                    "series": {
                                        "id": 2,
                                        "name": "Energize",
                                        "description": "Energize series"
                                    }
                                }
                            ]
                        }
                        """));

        verify(productService).getProducts(expectedRequest);
    }

    @Test
    @WithMockUser(username = "test@gmail.com", roles = {"ADMIN"})
    void updateProduct() throws Exception {

        UpdateProductRequest expectedRequest = UpdateProductRequest.builder()
                .id(1L)
                .name("test")
                .price(10.0)
                .description("test")
                .quantity(0L)
                .type("test")
                .seriesId(1L)
                .build();

        mockMvc.perform(put("/products/1")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .content("""
                        {
                            "name": "test",
                            "price": 10.0,
                            "description": "test",
                            "quantity": 0,
                            "type": "test",
                            "seriesId": 1
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(productService).updateProduct(expectedRequest);
    }

    @Test
    @WithMockUser(username = "test@gmail.com", roles = {"ADMIN"})
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(productService).deleteProduct(1L);
    }
}