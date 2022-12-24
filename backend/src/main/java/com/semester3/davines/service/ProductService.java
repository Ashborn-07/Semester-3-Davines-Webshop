package com.semester3.davines.service;

import com.semester3.davines.domain.requests.CreateProductRequest;
import com.semester3.davines.domain.requests.GetProductsRequest;
import com.semester3.davines.domain.requests.UpdateProductRequest;
import com.semester3.davines.domain.response.CreateProductResponse;
import com.semester3.davines.domain.response.GetAllProductsResponse;
import com.semester3.davines.domain.response.GetProductResponse;
import com.semester3.davines.domain.response.GetProductsResponse;

public interface ProductService {

    GetAllProductsResponse getAllProducts();

    GetProductsResponse getProducts(GetProductsRequest request);

    GetProductResponse getProduct(Long id);

    CreateProductResponse createProduct(CreateProductRequest request);

    void updateProduct(UpdateProductRequest request);

    void deleteProduct(long id);
}
