package com.semester3.davines.service.impl;

import com.semester3.davines.domain.response.GetAllOrdersResponse;
import com.semester3.davines.repository.OrderRepository;
import com.semester3.davines.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public GetAllOrdersResponse getAllOrders() {
        return GetAllOrdersResponse.builder()
                .orders(orderRepository.findAll().stream().map(OrderConverter::convert).toList())
                .build();
    }
}
