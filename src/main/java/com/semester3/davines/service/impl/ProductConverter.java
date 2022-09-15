package com.semester3.davines.service.impl;


import com.semester3.davines.repository.entity.domain.Product;
import com.semester3.davines.repository.entity.ProductEntity;

final class ProductConverter {
    private ProductConverter() {}

    public static Product convert(ProductEntity product) {
        return  Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .type(product.getType())
                .seriesId(product.getSeriesId())
                .build();
    }
}
