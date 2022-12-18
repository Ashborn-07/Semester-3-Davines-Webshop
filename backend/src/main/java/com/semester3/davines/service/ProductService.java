package com.semester3.davines.service;

import com.semester3.davines.domain.*;

public interface ProductService {

    GetAllProductsResponse getAllProducts();

    GetProductsResponse getProducts(GetProductsRequest request);

    GetProductResponse getProduct(Long id);

    CreateProductResponse createProduct(CreateProductRequest request);

    void updateProduct(UpdateProductRequest request);

    void deleteProduct(long id);
}
