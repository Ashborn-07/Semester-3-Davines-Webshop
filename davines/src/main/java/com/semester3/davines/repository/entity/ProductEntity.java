package com.semester3.davines.repository.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Long seriesId;
}
