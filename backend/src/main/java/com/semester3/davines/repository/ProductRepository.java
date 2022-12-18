package com.semester3.davines.repository;

import com.semester3.davines.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> getAllProductsByType(String type);

    List<ProductEntity> findAllBySeriesId(long seriesId);
}
