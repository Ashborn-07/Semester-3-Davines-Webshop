package com.semester3.davines.controller;

import com.semester3.davines.configuration.security.isauthenticated.IsAuthenticated;
import com.semester3.davines.domain.requests.CreateSeriesRequest;
import com.semester3.davines.domain.requests.GetAllProductsFromSeriesRequest;
import com.semester3.davines.domain.requests.UpdateSeriesRequest;
import com.semester3.davines.domain.response.CreateSeriesResponse;
import com.semester3.davines.domain.response.GetAllProductsFromSeriesResponse;
import com.semester3.davines.domain.response.GetAllSeriesResponse;
import com.semester3.davines.service.SeriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/series")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<CreateSeriesResponse> createSeries(@RequestBody @Valid CreateSeriesRequest request) {
        CreateSeriesResponse response = seriesService.createSeries(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetAllSeriesResponse> getAllSeries() {
        GetAllSeriesResponse response = seriesService.getSeries();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<GetAllProductsFromSeriesResponse> getAllProductsFromSeries(@PathVariable(value = "id") Long id, GetAllProductsFromSeriesRequest request) {
        request.setSeriesId(id);
        GetAllProductsFromSeriesResponse response = seriesService.getSeriesAndProducts(request);
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping({"/update/{id}"})
    public ResponseEntity<Void> updateSeries(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateSeriesRequest request) {
        request.setId(id);
        seriesService.updateSeries(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping({"/delete/{seriesId}"})
    public ResponseEntity<Void> deleteSeries(@PathVariable long seriesId) {
        seriesService.deleteSeries(seriesId);
        return ResponseEntity.noContent().build();
    }
}
