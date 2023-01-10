package com.semester3.davines.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProducts {

    @EmbeddedId
    private OrderProductsId id;

    @ManyToOne
    @MapsId("order_id")
    private OrderEntity order;

    @ManyToOne
    @MapsId("product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private Long quantity;
}
