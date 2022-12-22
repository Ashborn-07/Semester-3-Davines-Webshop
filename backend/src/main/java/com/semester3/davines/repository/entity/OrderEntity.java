package com.semester3.davines.repository.entity;

import com.semester3.davines.repository.entity.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "order_date")
    private String orderDate;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @NotNull
    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "products")
    @ManyToMany
    private List<ProductEntity> products;
}
