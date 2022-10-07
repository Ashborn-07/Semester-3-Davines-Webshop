package com.semester3.davines.service.impl;

import com.semester3.davines.domain.*;
import com.semester3.davines.repository.ProductRepository;
import com.semester3.davines.repository.SeriesRepository;
import com.semester3.davines.repository.entity.ProductEntity;
import com.semester3.davines.repository.entity.SeriesEntity;
import com.semester3.davines.service.ProductService;
import com.semester3.davines.service.exception.InvalidProductException;
import com.semester3.davines.service.exception.InvalidSeriesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private SeriesRepository seriesRepository;

    @Override
    public GetAllProductsResponse getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return GetAllProductsResponse.builder()
                .products(productEntities.stream().map(ProductConverter::convert).toList())
                .build();
    }

    @Override
    public GetProductsResponse getProducts(GetProductsRequest request) {
        List<Product> products = this.productRepository.getAllProductsByType(request.getProductType())
                .stream()
                .map(ProductConverter::convert)
                .toList();

        return GetProductsResponse.builder()
                .productList(products)
                .build();
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        ProductEntity savedProduct = saveNewProduct(request);

        return CreateProductResponse.builder()
                .productId(savedProduct.getId())
                .build();
    }

    @Override
    public void updateProduct(UpdateProductRequest request) {
        Optional<ProductEntity> productOptional = productRepository.findById(request.getId());

        if (productOptional.isEmpty()) {
            throw new InvalidProductException();
        }

        ProductEntity productEntity = productOptional.get();
        updateFields(request, productEntity);
    }

    @Override
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    private void updateFields(UpdateProductRequest request, ProductEntity productEntity) {
        productEntity.setName(request.getName());
        productEntity.setDescription(request.getDescription());
        productEntity.setPrice(request.getPrice());
        productEntity.setType(request.getType());
        productEntity.setQuantity(request.getQuantity());

        productRepository.save(productEntity);
    }

    private ProductEntity saveNewProduct(CreateProductRequest request) {

        SeriesEntity seriesEntity = seriesRepository.findById(request.getSeriesId()).orElseThrow(InvalidSeriesException::new);

        ProductEntity product = ProductEntity.builder()
                .description(request.getDescription())
                .name(request.getName())
                .price(request.getPrice())
                .type(request.getType())
                .quantity(request.getQuantity())
                .series(seriesEntity)
                .build();

        return productRepository.save(product);
    }
}
