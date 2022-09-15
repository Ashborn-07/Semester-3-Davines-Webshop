package com.semester3.davines.service;

import com.semester3.davines.repository.entity.domain.CreateSeriesRequest;
import com.semester3.davines.repository.entity.domain.CreateSeriesResponse;

public interface CreateSeriesUseCase {

    CreateSeriesResponse createSeries(CreateSeriesRequest request);
}
