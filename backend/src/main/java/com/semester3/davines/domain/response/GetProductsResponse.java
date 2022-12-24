package com.semester3.davines.domain.response;

import com.semester3.davines.domain.Product;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsResponse {
    private List<Product> productList;
}
