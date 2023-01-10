package com.semester3.davines.service.impl;


import com.semester3.davines.domain.models.Product;
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
                .image(product.getImage())
                .series(SeriesConverter.convert(product.getSeries()))
                .build();
    }

    public static ProductEntity convert(Product product) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .type(product.getType())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .series(SeriesConverter.convert(product.getSeries()))
                .build();

        productEntity.setId(product.getId());

        return productEntity;
    }
}
