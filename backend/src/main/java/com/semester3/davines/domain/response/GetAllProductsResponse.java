package com.semester3.davines.domain.response;

import com.semester3.davines.domain.Product;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsResponse {

    private List<Product> products;
}
