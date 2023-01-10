package com.semester3.davines.domain.response;

import com.semester3.davines.domain.models.Product;
import com.semester3.davines.domain.models.Series;
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
