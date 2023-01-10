package com.semester3.davines.service.impl;

import com.semester3.davines.domain.models.Series;
import com.semester3.davines.domain.requests.CreateSeriesRequest;
import com.semester3.davines.domain.requests.GetAllProductsFromSeriesRequest;
import com.semester3.davines.domain.requests.UpdateSeriesRequest;
import com.semester3.davines.domain.response.CreateSeriesResponse;
import com.semester3.davines.domain.response.GetAllProductsFromSeriesResponse;
import com.semester3.davines.domain.response.GetAllSeriesResponse;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.SeriesRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.repository.entity.SeriesEntity;
import com.semester3.davines.service.SeriesService;
import com.semester3.davines.service.exception.InvalidSeriesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeriesServiceImpl implements SeriesService {

    private SeriesRepository seriesRepository;

    private ProductRepository productRepository;

    @Override
    public GetAllProductsFromSeriesResponse getSeriesAndProducts(GetAllProductsFromSeriesRequest request) {
        List<ProductEntity> productEntityList = productRepository.findAllBySeriesId(request.getSeriesId());
        SeriesEntity seriesEntity = seriesRepository.findById(request.getSeriesId()).orElseThrow(InvalidSeriesException::new);

        return GetAllProductsFromSeriesResponse.builder()
                .series(SeriesConverter.convert(seriesEntity))
                .products(productEntityList.stream().map(ProductConverter::convert).toList())
                .build();
    }

    @Override
    public GetAllSeriesResponse getSeries() {
        List<SeriesEntity> seriesEntities = seriesRepository.findAll();

        List<Series> seriesList = seriesEntities
                .stream()
                .map(SeriesConverter::convert)
                .toList();

        final GetAllSeriesResponse response = new GetAllSeriesResponse();

        response.setSeries(seriesList);

        return response;
    }

    @Override
    public CreateSeriesResponse createSeries(CreateSeriesRequest request) {
        SeriesEntity seriesEntity = saveNewSeries(request);

        return CreateSeriesResponse.builder()
                .seriesId(seriesEntity.getId())
                .build();
    }

    @Override
    public void updateSeries(UpdateSeriesRequest request) {
        Optional<SeriesEntity> seriesOptional = seriesRepository.findById(request.getId());

        if (seriesOptional.isEmpty()) {
            throw new InvalidSeriesException();
        }

        SeriesEntity series = seriesOptional.get();
        updateFields(request, series);
    }

    @Override
    public void deleteSeries(long id) {
        seriesRepository.deleteById(id);
    }

    private void updateFields(UpdateSeriesRequest request, SeriesEntity series) {
        series.setId(request.getId());
        series.setDescription(request.getDescription());
        series.setName(request.getName());
        series.setImage(request.getImage());

        seriesRepository.save(series);
    }

    private SeriesEntity saveNewSeries(CreateSeriesRequest request) {

        SeriesEntity seriesEntity = SeriesEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .build();

        return this.seriesRepository.save(seriesEntity);
    }
}
