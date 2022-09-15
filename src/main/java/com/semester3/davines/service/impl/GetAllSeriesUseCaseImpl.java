package com.semester3.davines.service.impl;

import com.semester3.davines.repository.entity.domain.GetAllSeriesResponse;
import com.semester3.davines.repository.entity.domain.Series;
import com.semester3.davines.repository.entity.SeriesEntity;
import com.semester3.davines.repository.impl.FakeSeriesRepositoryImpl;
import com.semester3.davines.service.GetAllSeriesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllSeriesUseCaseImpl implements GetAllSeriesUseCase {

    private FakeSeriesRepositoryImpl seriesRepository;

    @Override
    public GetAllSeriesResponse getSeries() {
        List<SeriesEntity> seriesEntities = seriesRepository.findAll();

        List<Series> seriesList = seriesEntities
                .stream()
                .map(SeriesConverter::convert)
                .toList();

        final GetAllSeriesResponse response = new GetAllSeriesResponse();

        response.setSeriesList(seriesList);

        return response;
    }
}
