package com.semester3.davines.service;

import com.semester3.davines.repository.entity.domain.CreateProductRequest;
import com.semester3.davines.repository.entity.domain.CreateProductResponse;

public interface CreateProductUseCase {

    CreateProductResponse createProduct(CreateProductRequest request);
}
