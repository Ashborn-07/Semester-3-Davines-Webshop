package com.semester3.davines.service.impl;

import com.semester3.davines.repository.entity.domain.CreateProductRequest;
import com.semester3.davines.repository.entity.domain.CreateProductResponse;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.service.CreateProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        ProductEntity savedProduct = saveNewProduct(request);

        return CreateProductResponse.builder()
                .productId(savedProduct.getId())
                .build();
    }

    private ProductEntity saveNewProduct(CreateProductRequest request) {

        ProductEntity product = ProductEntity.builder()
                .description(request.getDescription())
                .name(request.getName())
                .type(request.getType())
                .seriesId(request.getSeriesId())
                .build();

        return productRepository.save(product);
    }
}
