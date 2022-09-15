package com.semester3.davines.service.impl;

import com.semester3.davines.repository.entity.domain.CreateSeriesRequest;
import com.semester3.davines.repository.entity.domain.CreateSeriesResponse;
import com.semester3.davines.repository.SeriesRepository;
import com.semester3.davines.repository.entity.SeriesEntity;
import com.semester3.davines.service.CreateSeriesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CreateSeriesUseCaseImpl implements CreateSeriesUseCase {

    private SeriesRepository seriesRepository;

    @Override
    public CreateSeriesResponse createSeries(CreateSeriesRequest request) {
        SeriesEntity seriesEntity = saveNewSeries(request);

        return CreateSeriesResponse.builder()
                .seriesId(seriesEntity.getId())
                .build();
    }

    private SeriesEntity saveNewSeries(CreateSeriesRequest request) {

        SeriesEntity seriesEntity = SeriesEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return this.seriesRepository.save(seriesEntity);
    }
}
