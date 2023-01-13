package com.semester3.davines.service;

import com.semester3.davines.domain.models.Order;
import com.semester3.davines.domain.requests.CreateOrderRequest;
import com.semester3.davines.domain.requests.UpdateOrderStatus;
import com.semester3.davines.domain.response.CreateOrderResponse;
import com.semester3.davines.domain.response.GetAllOrdersResponse;
import org.springframework.data.domain.Page;

public interface OrderService {

    Page<Order> getAllOrders(int page);

    CreateOrderResponse createOrder(CreateOrderRequest request);

    Page<Order> getAllOrdersByUserEmail(int page, Long id);

    GetAllOrdersResponse getLastThreeOrders(Long id);

    void updateProductQuantity(UpdateOrderStatus request, Long orderId);
}
