package com.semester3.davines.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Double price;
    private Long quantity;
    private String image;
    private Series series;
}
