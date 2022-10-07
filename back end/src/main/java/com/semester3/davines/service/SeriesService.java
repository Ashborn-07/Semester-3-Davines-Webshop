package com.semester3.davines.service;

import com.semester3.davines.domain.*;

public interface SeriesService {

    GetAllProductsFromSeriesResponse getProductsFromSeries(GetAllProductsFromSeriesRequest request);

    GetAllSeriesResponse getSeries();

    CreateSeriesResponse createSeries(CreateSeriesRequest request);

    void updateSeries(UpdateSeriesRequest request);

    void deleteSeries(long id);
}
