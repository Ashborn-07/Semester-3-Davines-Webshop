package com.semester3.davines.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductResponse {
    private Long productId;
}
