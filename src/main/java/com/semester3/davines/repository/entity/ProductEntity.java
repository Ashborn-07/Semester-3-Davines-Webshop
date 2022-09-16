package com.semester3.davines.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Double price;
    private Long seriesId;
}
