package com.semester3.davines.service;

import com.semester3.davines.repository.entity.domain.GetProductsRequest;
import com.semester3.davines.repository.entity.domain.GetProductsResponse;
import com.semester3.davines.repository.entity.domain.Product;

import java.util.List;
import java.util.Optional;

public interface GetProductsUseCase {
   GetProductsResponse getProducts(GetProductsRequest request);
}
