package com.semester3.davines.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllSeriesResponse {
    private List<Series> series;
}