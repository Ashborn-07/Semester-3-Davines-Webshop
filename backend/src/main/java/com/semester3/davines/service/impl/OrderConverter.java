package com.semester3.davines.service.impl;

import com.semester3.davines.domain.models.Order;
import com.semester3.davines.repository.entity.OrderEntity;

final class OrderConverter {

    private OrderConverter() {}

    public static Order convert(OrderEntity order) {
        return Order.builder()
                .id(order.getId())
                .firstName(order.getFirstName())
                .lastName(order.getLastName())
                .email(order.getEmail())
                .country(order.getCountry())
                .city(order.getCity())
                .address(order.getAddress())
                .phone(order.getPhone())
                .orderDate(order.getOrderDate())
                .status(order.getOrderStatus())
                .total(order.getTotalPrice())
                .build();
    }
}
