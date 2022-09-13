package com.semester3.davines.repository.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SeriesEntity {
    private Long id;

    private String name;

    private String description;

    private List<ProductEntity> products;
}
