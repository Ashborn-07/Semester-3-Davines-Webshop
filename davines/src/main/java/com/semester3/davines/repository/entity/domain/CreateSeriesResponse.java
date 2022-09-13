package com.semester3.davines.repository.entity.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSeriesResponse {
    private long seriesId;
}
