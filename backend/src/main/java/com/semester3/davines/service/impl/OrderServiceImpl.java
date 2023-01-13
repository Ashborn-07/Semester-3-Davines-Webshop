package com.semester3.davines.service.impl;

import com.semester3.davines.domain.models.Order;
import com.semester3.davines.domain.requests.CreateOrderRequest;
import com.semester3.davines.domain.response.CreateOrderResponse;
import com.semester3.davines.domain.response.GetAllOrdersResponse;
import com.semester3.davines.repository.OrderRepository;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.UserRepository;
import com.semester3.davines.repository.entity.OrderEntity;
import com.semester3.davines.repository.entity.UserEntity;
import com.semester3.davines.repository.entity.enums.OrderStatusEnum;
import com.semester3.davines.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Page<Order> getAllOrders(int page) {
        PageRequest pageRequest = PageRequest.of(page, 4);
        return orderRepository.findAll(pageRequest)
                .map(OrderConverter::convert);
    }

    @Override
    public Page<Order> getAllOrdersByUserEmail(int page, Long id) {
        PageRequest pageRequest = PageRequest.of(page, 6);
        if (userRepository.findById(id).isEmpty()) {
            throw new InvalidUserIdException();
        }
        UserEntity user = userRepository.findById(id).get();
        return orderRepository.findAllByEmail(pageRequest, user.getEmail())
                .map(OrderConverter::convert);
    }

    @Override
    public GetAllOrdersResponse getLastThreeOrders(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new InvalidUserIdException();
        }
        UserEntity user = userRepository.findById(id).get();
        List<OrderEntity> orderList = orderRepository.findAllByEmail(user.getEmail());

        if (orderList.size() > 3) {
            orderList = orderList.subList(orderList.size() - 3, orderList.size());
        }
        Collections.reverse(orderList);
        return GetAllOrdersResponse.builder()
                .orders(orderList.stream().map(OrderConverter::convert).toList())
                .build();
    }

    @Override
    public void updateProductQuantity(UpdateOrderStatus request, Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();

        if (orderEntity.getOrderStatus().equals(OrderStatusEnum.PENDING)) {
            for (var orderProduct : orderEntity.getProducts()) {
                ProductEntity product = productRepository.findById(orderProduct.getId()).orElseThrow();
                product.setQuantity(product.getQuantity() - orderProduct.getQuantity());
                productRepository.save(product);
            }
        }
        orderEntity.setOrderStatus(OrderStatusEnum.valueOf(request.getStatus()));
        orderRepository.save(orderEntity);
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        OrderEntity orderEntity = saveOrder(request);

        return CreateOrderResponse.builder()
                .orderId(orderEntity.getId())
                .build();
    }

    private OrderEntity saveOrder(CreateOrderRequest request) {
        OrderEntity orderEntity = OrderEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .orderDate(request.getOrderDate())
                .country(request.getCountry())
                .city(request.getCity())
                .address(request.getAddress())
                .phone(request.getPhone())
                .orderStatus(OrderStatusEnum.PENDING)
                .totalPrice(request.getTotalPrice())
                .build();

        return orderRepository.save(orderEntity);
    }
}
