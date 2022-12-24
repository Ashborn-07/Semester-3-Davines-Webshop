package com.semester3.davines.service.impl;

import com.semester3.davines.domain.*;
import com.semester3.davines.domain.requests.CreateSeriesRequest;
import com.semester3.davines.domain.requests.GetAllProductsFromSeriesRequest;
import com.semester3.davines.domain.requests.UpdateSeriesRequest;
import com.semester3.davines.domain.response.GetAllProductsFromSeriesResponse;
import com.semester3.davines.domain.response.GetAllSeriesResponse;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.SeriesRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.repository.entity.SeriesEntity;
import com.semester3.davines.service.exception.InvalidSeriesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeriesServiceImplTest {

    @Mock
    private SeriesRepository seriesRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SeriesServiceImpl seriesService;


    //Variables
    private SeriesEntity love;
    private SeriesEntity energize;
    private ProductEntity loveShampoo;
    private ProductEntity loveConditioner;

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

        loveShampoo = ProductEntity.builder()
                .id(1L)
                .name("Love Shampoo")
                .description("description")
                .type("shampoo")
                .series(love)
                .build();

        loveConditioner = ProductEntity.builder()
                .id(2L)
                .name("Love Conditioner")
                .description("description")
                .type("conditioner")
                .series(love)
                .build();
    }

    @Test
    void getProductsFromSeries() {
        when(productRepository.findAllBySeriesId(anyLong()))
                .thenReturn(List.of(loveShampoo, loveConditioner));

        when(seriesRepository.findById(anyLong()))
                .thenReturn(java.util.Optional.ofNullable(love));

        GetAllProductsFromSeriesRequest request = GetAllProductsFromSeriesRequest.builder()
                .seriesId(1L)
                .build();

        GetAllProductsFromSeriesResponse actualResult = seriesService.getSeriesAndProducts(request);

        Product loveeShampoo = Product.builder()
                .id(1L)
                .name("Love Shampoo")
                .description("description")
                .type("shampoo")
                .series(SeriesConverter.convert(love))
                .build();

        Product loveeConditioner = Product.builder()
                .id(2L)
                .name("Love Conditioner")
                .description("description")
                .type("conditioner")
                .series(SeriesConverter.convert(love))
                .build();

        GetAllProductsFromSeriesResponse expectedResult = GetAllProductsFromSeriesResponse.builder()
                .series(SeriesConverter.convert(love))
                .products(List.of(loveeShampoo, loveeConditioner))
                .build();

        assertEquals(expectedResult, actualResult);
        verify(productRepository).findAllBySeriesId(anyLong());
    }

    @Test
    void getSeries() {
        when(seriesRepository.findAll())
                .thenReturn(List.of(love, energize));

        GetAllSeriesResponse actualResult = seriesService.getSeries();

        Series love = Series.builder()
                .id(1L)
                .name("Love")
                .description("description")
                .build();

        Series energize = Series.builder()
                .id(2L)
                .name("Energize")
                .description("description")
                .build();

        GetAllSeriesResponse expectedResult = GetAllSeriesResponse.builder()
                .series(List.of(love, energize))
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

    @Test
    void update_seriesExceptionSeriesNotFound() {
        when(seriesRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(null));

        UpdateSeriesRequest request = UpdateSeriesRequest.builder()
                .id(1L)
                .name("Love")
                .description("description1")
                .build();

        assertThrows(InvalidSeriesException.class, () -> seriesService.updateSeries(request));
    }
}