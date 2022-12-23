package com.semester3.davines.service.impl;

import com.semester3.davines.domain.*;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.SeriesRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.repository.entity.SeriesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private SeriesRepository seriesRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    //variables
    private ProductEntity loveEntity;
    private ProductEntity energizeEntity;
    private Product love;
    private Product energize;
    private SeriesEntity series;

    @BeforeEach
    void setUp() {
        series = SeriesEntity.builder()
                .name("Davines").description("description").build();

        loveEntity = ProductEntity.builder()
                .id(1L)
                .name("Love")
                .type("shampoo")
                .series(series)
                .build();

        energizeEntity = ProductEntity.builder()
                .id(2L)
                .name("Energize")
                .type("shampoo")
                .series(series)
                .build();

        love = Product.builder()
                .id(1L)
                .name("Love")
                .type("shampoo")
                .series(SeriesConverter.convert(series))
                .build();

        energize = Product.builder()
                .id(2L)
                .name("Energize")
                .type("shampoo")
                .series(SeriesConverter.convert(series))
                .build();
    }

    @Test
    void getProducts() {

        when(productRepository.getAllProductsByType(any()))
                .thenReturn(List.of(loveEntity, energizeEntity));

        GetProductsRequest request = GetProductsRequest.builder().productType("shampoo").build();
        GetProductsResponse actualResult = productService.getProducts(request);

        GetProductsResponse expectedResult = GetProductsResponse.builder()
                .productList(List.of(love, energize))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(productRepository).getAllProductsByType(any());
    }

    @Test
    void createProduct_ShouldCreateProduct() {
        when(productRepository.save(any())).thenReturn(loveEntity);
        when(seriesRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(series));

        CreateProductRequest request = CreateProductRequest.builder()
                .name("Love")
                .type("shampoo")
                .seriesId(1L)
                .build();

        CreateProductResponse actualResult = productService.createProduct(request);

        CreateProductResponse expectedResult = CreateProductResponse.builder()
                .productId(love.getId())
                .build();

        assertEquals(expectedResult, actualResult);
        verify(productRepository).save(any());
        verify(seriesRepository).findById(any());
    }

    @Test
    void updateProduct_VerifyMethodCall() {
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(loveEntity));
        when(productRepository.save(any())).thenReturn(loveEntity);

        UpdateProductRequest request = UpdateProductRequest.builder()
                .id(1L)
                .name("Love")
                .type("shampoo")
                .seriesId(1L)
                .build();

        productService.updateProduct(request);

        verify(productRepository).findById(any());
        verify(productRepository).save(any());
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void getProductDetails() {
        //TODO: implement test
    }

    //TODO: update Product with exception test

    //TODO: Implement test getAllProducts
}