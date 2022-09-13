package com.semester3.davines.repository.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    private Long id;
    private String name;
    private String description;
    private List<Product> products;
}
