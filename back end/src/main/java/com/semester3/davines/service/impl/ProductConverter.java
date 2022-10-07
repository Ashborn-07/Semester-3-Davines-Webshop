package com.semester3.davines.service.impl;


import com.semester3.davines.domain.Product;
import com.semester3.davines.repository.entity.ProductEntity;

final class ProductConverter {
    private ProductConverter() {}

    public static Product convert(ProductEntity product) {
        return  Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .type(product.getType())
                .quantity(product.getQuantity())
                .series(SeriesConverter.convert(product.getSeries()))
                .build();
    }
}
