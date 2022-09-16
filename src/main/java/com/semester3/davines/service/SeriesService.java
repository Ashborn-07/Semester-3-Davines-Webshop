package com.semester3.davines.service;

import com.semester3.davines.domain.CreateSeriesRequest;
import com.semester3.davines.domain.CreateSeriesResponse;
import com.semester3.davines.domain.GetAllSeriesResponse;
import com.semester3.davines.domain.UpdateSeriesRequest;

public interface SeriesService {
    GetAllSeriesResponse getSeries();

    CreateSeriesResponse createSeries(CreateSeriesRequest request);

    void updateSeries(UpdateSeriesRequest request);

    void deleteSeries(long id);
}
