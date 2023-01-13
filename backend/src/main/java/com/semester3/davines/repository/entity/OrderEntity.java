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
public class OrderEntity extends BaseEntity{

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "order_date")
    private String orderDate;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "phone_number")
    private String phone;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @NotNull
    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "order",
            fetch = FetchType.EAGER)
    private List<OrderProductsEntity> products;
}
