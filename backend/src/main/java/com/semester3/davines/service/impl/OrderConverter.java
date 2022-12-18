package com.semester3.davines.service.impl;

import com.semester3.davines.domain.Order;
import com.semester3.davines.repository.entity.OrderEntity;

final class OrderConverter {

    private OrderConverter() {}

    public static Order convert(OrderEntity order) {
        return Order.builder()
                .id(order.getId())
                .date(order.getOrderDate())
                .status(order.getOrderStatus())
                .products(order.getProducts().stream().map(ProductConverter::convert).toList())
                .build();
    }
}
