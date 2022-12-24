package com.semester3.davines.service;

import com.semester3.davines.domain.requests.CreateSeriesRequest;
import com.semester3.davines.domain.requests.GetAllProductsFromSeriesRequest;
import com.semester3.davines.domain.requests.UpdateSeriesRequest;
import com.semester3.davines.domain.response.CreateSeriesResponse;
import com.semester3.davines.domain.response.GetAllProductsFromSeriesResponse;
import com.semester3.davines.domain.response.GetAllSeriesResponse;

public interface SeriesService {

    GetAllProductsFromSeriesResponse getSeriesAndProducts(GetAllProductsFromSeriesRequest request);

    GetAllSeriesResponse getSeries();

    CreateSeriesResponse createSeries(CreateSeriesRequest request);

    void updateSeries(UpdateSeriesRequest request);

    void deleteSeries(long id);
}
