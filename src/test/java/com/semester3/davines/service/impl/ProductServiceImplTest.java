package com.semester3.davines.service.impl;

import com.semester3.davines.domain.*;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.impl.FakeProductRepositoryImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository = new FakeProductRepositoryImpl();
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProducts() {
        //Arrange
        GetProductsRequest request = GetProductsRequest.builder().productType("Shampoo").build();
        //Act
        GetProductsResponse response = productService.getProducts(request);
        //Assert
        Assertions.assertEquals(1, response.getProductList().size());
    }

    @Test
    void getProductsZero() {
        //Arrange
        GetProductsRequest request = GetProductsRequest.builder().productType("KAKAA").build();
        //Act
        GetProductsResponse response = productService.getProducts(request);
        //Assert
        Assertions.assertEquals(0, response.getProductList().size());
    }

    @Test
    void createProduct() {
        //Arrange
        CreateProductRequest request = CreateProductRequest.builder()
                .name("Ivan")
                .description("Shampoo")
                .price(10.00)
                .type("Shampoo")
                .build();
        //Act
        CreateProductResponse response = productService.createProduct(request);
        //Assert
        Assertions.assertEquals(3L, response.getProductId());
        Assertions.assertEquals(2, productRepository.getAllProductsByType("Shampoo").size());
    }

    @Test
    void updateProduct() {
        //Arrange
        UpdateProductRequest request = UpdateProductRequest.builder()
                .id(1L)
                .name("Kiro")
                .description("Shampoo")
                .price(10.00)
                .type("Balsam")
                .build();
        //Act
        productService.updateProduct(request);
        //Assert
        Assertions.assertEquals("Kiro", productRepository.findById(1L).get().getName());
    }

    @Test
    void deleteProduct() {
        //Arrange
        long id = 1L;
        //Act
        productService.deleteProduct(id);
        //Assert
        Assertions.assertEquals(0, productRepository.getAllProductsByType("Shampoo").size());
    }
}