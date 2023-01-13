package com.semester3.davines.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length(min = 0, max = 10000)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Length(min = 3, max = 40)
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "quantity")
    private Long quantity;

    @NotNull
    @Column(name = "image")
    private String image;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "series_id")
    private SeriesEntity series;

    @OneToMany(mappedBy = "product")
    private List<OrderProductsEntity> orderProductEntities;
}
