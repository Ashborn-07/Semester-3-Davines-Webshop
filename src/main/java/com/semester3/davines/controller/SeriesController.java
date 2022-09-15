package com.semester3.davines.controller;

import com.semester3.davines.repository.entity.domain.CreateSeriesRequest;
import com.semester3.davines.repository.entity.domain.CreateSeriesResponse;
import com.semester3.davines.repository.entity.domain.GetAllSeriesResponse;
import com.semester3.davines.service.CreateSeriesUseCase;
import com.semester3.davines.service.GetAllSeriesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/series")
@AllArgsConstructor
public class SeriesController {

    private final CreateSeriesUseCase createSeriesUseCase;

    private final GetAllSeriesUseCase getAllSeriesUseCase;

    @PostMapping()
    public ResponseEntity<CreateSeriesResponse> createSeries(@RequestBody @Valid CreateSeriesRequest request) {
        CreateSeriesResponse response = createSeriesUseCase.createSeries(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetAllSeriesResponse> getAllSeries() {
        GetAllSeriesResponse response = getAllSeriesUseCase.getSeries();
        return ResponseEntity.ok(response);
    }
}
