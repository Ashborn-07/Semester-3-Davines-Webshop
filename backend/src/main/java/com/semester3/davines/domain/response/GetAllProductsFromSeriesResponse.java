package com.semester3.davines.domain.response;

import com.semester3.davines.domain.Product;
import com.semester3.davines.domain.Series;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsFromSeriesResponse {

    private Series series;
    private List<Product> products;
}
