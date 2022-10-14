package com.semester3.davines.controller;

import com.semester3.davines.domain.CreateProductRequest;
import com.semester3.davines.domain.CreateProductResponse;
import com.semester3.davines.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts() {
        assert true;
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
    void testGetAllProducts() {
        assert true;
    }

    @Test
    void updateProduct() {
        assert true;
    }

    @Test
    void deleteProduct() {
        assert true;
    }
}