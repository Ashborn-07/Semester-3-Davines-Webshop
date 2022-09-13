package com.semester3.davines.service.impl;

import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.repository.entity.domain.GetProductsRequest;
import com.semester3.davines.repository.entity.domain.GetProductsResponse;
import com.semester3.davines.repository.entity.domain.Product;
import com.semester3.davines.service.GetProductsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetProductsUseCaseImpl implements GetProductsUseCase {

    private ProductRepository productRepository;

    @Override
    public GetProductsResponse getProducts(GetProductsRequest request) {
        List<Product> products = this.productRepository.getAllProductsByType(request.getProductType())
                .stream()
                .map(ProductConverter::convert)
                .toList();

        return GetProductsResponse.builder()
                .productList(products)
                .build();
    }
}
