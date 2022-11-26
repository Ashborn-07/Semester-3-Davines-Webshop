package com.semester3.davines.service.impl;

import com.semester3.davines.domain.Series;
import com.semester3.davines.repository.entity.SeriesEntity;

final class SeriesConverter {
    private SeriesConverter() {
    }

    public static Series convert(SeriesEntity series) {
        return Series.builder()
                .id(series.getId())
                .name(series.getName())
                .description(series.getDescription())
                .image(series.getImage())
                .build();
    }
}
