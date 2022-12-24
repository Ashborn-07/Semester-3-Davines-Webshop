package com.semester3.davines.controller;

import com.semester3.davines.configuration.security.isauthenticated.IsAuthenticated;
import com.semester3.davines.domain.requests.CreateProductRequest;
import com.semester3.davines.domain.requests.GetProductsRequest;
import com.semester3.davines.domain.requests.UpdateProductRequest;
import com.semester3.davines.domain.response.CreateProductResponse;
import com.semester3.davines.domain.response.GetAllProductsResponse;
import com.semester3.davines.domain.response.GetProductResponse;
import com.semester3.davines.domain.response.GetProductsResponse;
import com.semester3.davines.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/type/{productType}")
    public ResponseEntity<GetProductsResponse> getAllProductsWithType(@PathVariable(value = "productType") final String productType) {
        GetProductsRequest request = GetProductsRequest.builder().productType(productType).build();
        GetProductsResponse response = productService.getProducts(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("details/{id}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable(value = "id") final Long id) {
        GetProductResponse response = productService.getProduct(id);
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable(value = "id") long id,
                                              @RequestBody @Valid UpdateProductRequest request) {
        request.setId(id);
        productService.updateProduct(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
