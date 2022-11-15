package com.semester3.davines.controller;

import com.semester3.davines.domain.*;

import com.semester3.davines.service.SeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class SeriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeriesService seriesService;

    private Series loveSeries;
    private Series energizeSeries;
    private Product lovedProduct;
    private Product energizedProduct;

    @BeforeEach
    void setUp() {
        loveSeries = Series.builder()
                .id(1L)
                .name("Love")
                .description("Love series")
                .build();

        energizeSeries = Series.builder()
                .id(2L)
                .name("Energize")
                .description("Energize series")
                .build();

        lovedProduct = Product.builder()
                .id(1L)
                .name("Loved")
                .description("Loved product")
                .quantity(10L)
                .price(100.0)
                .type("shampoo")
                .series(loveSeries)
                .build();

        energizedProduct = Product.builder()
                .id(2L)
                .name("Energized")
                .description("Energized product")
                .quantity(10L)
                .price(100.0)
                .type("shampoo")
                .series(energizeSeries)
                .build();
    }

    @Test
    void createSeries() {
    }

    @Test
    @WithAnonymousUser
    void getAllSeries() throws Exception {
        when(seriesService.getSeries())
                .thenReturn(GetAllSeriesResponse.builder()
                        .series(List.of(loveSeries, energizeSeries))
                        .build());

        mockMvc.perform(get("/series"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                            "series": [
                                {
                                    "id": 1,
                                    "name": "Love",
                                    "description": "Love series"
                                },
                                {
                                    "id": 2,
                                    "name": "Energize",
                                    "description": "Energize series"
                                }
                            ]
                        }
                        """));

        verify(seriesService).getSeries();
    }

    @Test
    @WithAnonymousUser
    void getAllProductsFromSeries() throws Exception {
        GetAllProductsFromSeriesRequest expectedRequest = GetAllProductsFromSeriesRequest.builder()
                .seriesId(1L)
                .build();

        when(seriesService.getSeriesAndProducts(expectedRequest))
                .thenReturn(GetAllProductsFromSeriesResponse.builder()
                        .series(loveSeries)
                        .products(List.of(lovedProduct))
                        .build());

        mockMvc.perform(get("/series/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                            "series": {
                                     "id": 1,
                                     "name": "Love",
                                     "description": "Love series"
                                 },
                                 "products": [
                                     {
                                         "id": 1,
                                         "name": "Loved",
                                         "description": "Loved product",
                                         "type": "shampoo",
                                         "price": 100.0,
                                         "quantity": 10,
                                         "series": {
                                             "id": 1,
                                             "name": "Love",
                                             "description": "Love series"
                                         }
                                     }
                                 ]
                        }
                        """));

        verify(seriesService).getSeriesAndProducts(expectedRequest);
    }

    @Test
    void updateSeries() {
    }

    @Test
    void deleteSeries() {
    }
}