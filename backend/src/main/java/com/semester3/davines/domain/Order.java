package com.semester3.davines.domain;

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
    private String name;
    private String email;
    private String address;
    private String date;
    private OrderStatusEnum status;
    private Double total;
    private List<Product> products;
}
