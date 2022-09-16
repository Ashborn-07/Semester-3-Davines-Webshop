package com.semester3.davines.repository.impl;

import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeProductRepositoryImpl implements ProductRepository {

    private static long NEXT_ID = 3;

    private final List<ProductEntity> savedProducts;

    public FakeProductRepositoryImpl() {
        this.savedProducts = new ArrayList<>();

        this.savedProducts.add(new ProductEntity(1L, "Shampoo", "Shampoo", "Shampoo", 10.00, 1L));
        this.savedProducts.add(new ProductEntity(2L, "Conditioner", "Conditioner", "Conditioner", 10.00, 1L));
    }

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
