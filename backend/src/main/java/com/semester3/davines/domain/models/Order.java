package com.semester3.davines.domain.models;

import com.semester3.davines.repository.entity.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String city;
    private String address;
    private String phone;
    private String orderDate;
    private OrderStatusEnum status;
    private Double total;
    private List<OrderProducts> products;
}
