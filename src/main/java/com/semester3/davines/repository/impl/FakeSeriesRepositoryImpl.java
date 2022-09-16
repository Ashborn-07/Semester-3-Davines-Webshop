package com.semester3.davines.repository.impl;

import com.semester3.davines.domain.Product;
import com.semester3.davines.repository.SeriesRepository;
import com.semester3.davines.repository.entity.SeriesEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeSeriesRepositoryImpl implements SeriesRepository {

    private static long NEXT_ID = 1;

    private final List<SeriesEntity> savedSeries;

    public FakeSeriesRepositoryImpl() {
        this.savedSeries = new ArrayList<>();
    }

    @Override
    public List<SeriesEntity> findAll() {
        return Collections.unmodifiableList(this.savedSeries);
    }

    @Override
    public Optional<SeriesEntity> findById(long seriesId) {
        return this.savedSeries.stream()
                .filter(seriesEntity -> seriesEntity.getId().equals(seriesId))
                .findFirst();
    }

    @Override
    public SeriesEntity save(SeriesEntity series) {
        if (series.getId() == null) {
            series.setId(NEXT_ID);
            NEXT_ID++;
            this.savedSeries.add(series);
        }
        return series;
    }

    @Override
    public void deleteById(long seriesId) {
        this.savedSeries.removeIf(seriesEntity -> seriesEntity.getId().equals(seriesId));
    }

    @Override
    public Optional<List<Product>> getAllProductsFromSeriesById(long seriesId) {
        return Optional.empty();
    }
}
