package com.semester3.davines.repository;

import com.semester3.davines.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> getAllProductsByType(String type);

    List<ProductEntity> findAllBySeriesId(long seriesId);
}
