package com.semester3.davines.controller;

import com.semester3.davines.domain.CreateSeriesRequest;
import com.semester3.davines.domain.CreateSeriesResponse;
import com.semester3.davines.domain.GetAllSeriesResponse;
import com.semester3.davines.domain.UpdateSeriesRequest;
import com.semester3.davines.service.SeriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/series")
@AllArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    @PostMapping()
    public ResponseEntity<CreateSeriesResponse> createSeries(@RequestBody @Valid CreateSeriesRequest request) {
        CreateSeriesResponse response = seriesService.createSeries(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetAllSeriesResponse> getAllSeries() {
        GetAllSeriesResponse response = seriesService.getSeries();
        return ResponseEntity.ok(response);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<Void> updateSeries(@PathVariable(value = "id") Long id, @RequestBody @Valid UpdateSeriesRequest request) {
        request.setId(id);
        seriesService.updateSeries(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping({"{seriesId}"})
    public ResponseEntity<Void> deleteSeries(@PathVariable long seriesId) {
        seriesService.deleteSeries(seriesId);
        return ResponseEntity.noContent().build();
    }
}
