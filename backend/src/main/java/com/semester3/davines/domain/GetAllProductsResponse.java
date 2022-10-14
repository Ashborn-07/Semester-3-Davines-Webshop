package com.semester3.davines.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsResponse {

    private List<Product> products;
}
