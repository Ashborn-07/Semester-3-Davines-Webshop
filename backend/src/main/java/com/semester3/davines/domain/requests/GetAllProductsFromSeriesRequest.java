package com.semester3.davines.domain.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsFromSeriesRequest {

    private Long seriesId;
}
