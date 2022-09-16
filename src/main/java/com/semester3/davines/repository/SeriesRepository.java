package com.semester3.davines.repository;

import com.semester3.davines.domain.Product;
import com.semester3.davines.repository.entity.SeriesEntity;

import java.util.*;

public interface SeriesRepository {

    List<SeriesEntity> findAll();

    Optional<SeriesEntity> findById(long seriesId);

    SeriesEntity save(SeriesEntity series);

    void deleteById(long seriesId);

    Optional<List<Product>> getAllProductsFromSeriesById(long seriesId);
}
