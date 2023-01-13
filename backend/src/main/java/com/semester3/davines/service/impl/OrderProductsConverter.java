package com.semester3.davines.service.impl;

import com.semester3.davines.domain.models.OrderProducts;
import com.semester3.davines.repository.entity.OrderProductsEntity;

final class OrderProductsConverter {

    private OrderProductsConverter() {}

    public static OrderProducts convert(OrderProductsEntity orderProducts) {
        return OrderProducts.builder()
                .id(orderProducts.getId())
                .product(ProductConverter.convert(orderProducts.getProduct()))
                .quantity(orderProducts.getQuantity())
                .build();
    }
}
