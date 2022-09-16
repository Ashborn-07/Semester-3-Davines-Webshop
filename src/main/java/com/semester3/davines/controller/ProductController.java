package com.semester3.davines.controller;

import com.semester3.davines.domain.*;
import com.semester3.davines.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{productType}")
    public ResponseEntity<GetProductsResponse> getAllProducts(@PathVariable(value = "productType") final String productType) {
        GetProductsRequest request = GetProductsRequest.builder().productType(productType).build();
        GetProductsResponse response = productService.getProducts(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateProductRequest request) {
        request.setId(id);
        productService.updateProduct(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}