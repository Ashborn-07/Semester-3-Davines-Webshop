package com.semester3.davines.repository;

import com.semester3.davines.repository.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<ProductEntity> findAllBySeriesId(long seriesId);

    ProductEntity save(ProductEntity product);

    void deleteById(long productId);

    Optional<ProductEntity> findById(long productId);
}
