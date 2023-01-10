package com.semester3.davines.domain.response;

import com.semester3.davines.domain.models.Product;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsResponse {
    private List<Product> productList;
}
