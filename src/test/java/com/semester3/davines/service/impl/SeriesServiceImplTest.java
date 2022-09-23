package com.semester3.davines.service.impl;

import com.semester3.davines.domain.CreateSeriesRequest;
import com.semester3.davines.domain.GetAllSeriesResponse;
import com.semester3.davines.domain.Series;
import com.semester3.davines.domain.UpdateSeriesRequest;
import com.semester3.davines.repository.SeriesRepository;
import com.semester3.davines.repository.entity.SeriesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeriesServiceImplTest {

    @Mock
    private SeriesRepository seriesRepository;

    @InjectMocks
    private SeriesServiceImpl seriesService;

    private SeriesEntity love;
    private SeriesEntity energize;

    @BeforeEach
    void setUp() {
        love = SeriesEntity.builder()
                .id(1L)
                .name("Love")
                .description("description")
                .build();

        energize = SeriesEntity.builder()
                .id(2L)
                .name("Energize")
                .description("description")
                .build();
    }

    @Test
    void getProductsFromSeries() {
        //TODO test
    }

    @Test
    void getSeries() {
        when(seriesRepository.findAll())
                .thenReturn(List.of(love, energize));

        GetAllSeriesResponse actualResult = seriesService.getSeries();

        Series love = Series.builder()
                .name("Love")
                .description("description")
                .build();

        Series energize = Series.builder()
                .name("Energize")
                .description("description")
                .build();

        GetAllSeriesResponse expectedResult = GetAllSeriesResponse.builder()
                .seriesList(List.of(love, energize))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(seriesRepository).findAll();
    }

    @Test
    void createSeries() {
        when(seriesRepository.save(any(SeriesEntity.class)))
                .thenReturn(love);

        CreateSeriesRequest request = CreateSeriesRequest.builder()
                .name("Love")
                .description("description")
                .build();

        seriesService.createSeries(request);

        verify(seriesRepository).save(any(SeriesEntity.class));
    }

    @Test
    void updateSeries() {
        when(seriesRepository.save(any(SeriesEntity.class)))
                .thenReturn(love);
        when(seriesRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(love));

        UpdateSeriesRequest request = UpdateSeriesRequest.builder()
                .id(1L)
                .name("Love")
                .description("description1")
                .build();

        seriesService.updateSeries(request);

        verify(seriesRepository).save(any(SeriesEntity.class));
    }

    @Test
    void deleteSeries() {
        seriesService.deleteSeries(1L);
        verify(seriesRepository).deleteById(1L);
    }
}