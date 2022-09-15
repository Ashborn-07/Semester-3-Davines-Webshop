package com.semester3.davines.repository.impl;

import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeProductRepositoryImpl implements ProductRepository {

    private static long NEXT_ID = 1;

    private final List<ProductEntity> savedProducts;

    public FakeProductRepositoryImpl() { this.savedProducts = new ArrayList<>();  }

    @Override
    public List<ProductEntity> findAllBySeriesId(long seriesId) {
        return this.savedProducts.stream()
                .filter(productEntity -> productEntity.getSeriesId().equals(seriesId))
                .toList();
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        if (product.getId() == null) {
            product.setId(NEXT_ID);
            NEXT_ID++;
            this.savedProducts.add(product);
        }
        return product;
    }

    @Override
    public void deleteById(long productId) {
        this.savedProducts.removeIf(productEntity -> productEntity.getId().equals(productId));
    }

    @Override
    public Optional<ProductEntity> findById(long productId) {
        return this.savedProducts.stream()
                .filter(productEntity -> productEntity.getId().equals(productId))
                .findFirst();
    }

    @Override
    public List<ProductEntity> getAllProductsByType(String type) {
        return this.savedProducts.stream()
                .filter(productEntity -> productEntity.getType().equalsIgnoreCase(type))
                .toList();
    }
}
