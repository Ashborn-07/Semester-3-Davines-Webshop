package com.semester3.davines.domain.response;

import com.semester3.davines.domain.Series;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllSeriesResponse {
    private List<Series> series;
}
