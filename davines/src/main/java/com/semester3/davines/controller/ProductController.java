package com.semester3.davines.controller;

import com.semester3.davines.repository.entity.domain.CreateProductRequest;
import com.semester3.davines.repository.entity.domain.CreateProductResponse;
import com.semester3.davines.repository.entity.domain.GetProductsRequest;
import com.semester3.davines.repository.entity.domain.GetProductsResponse;
import com.semester3.davines.service.CreateProductUseCase;
import com.semester3.davines.service.GetProductsUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class ProductController {

    private final GetProductsUseCase getProductsUseCase;

    private final CreateProductUseCase createProductUseCase;
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = createProductUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{productType}")
    public ResponseEntity<GetProductsResponse> getAllProducts(@PathVariable(value = "productType") final String productType) {
        GetProductsRequest request = GetProductsRequest.builder().productType(productType).build();
        GetProductsResponse response = getProductsUseCase.getProducts(request);
        return ResponseEntity.ok(response);
    }
}
