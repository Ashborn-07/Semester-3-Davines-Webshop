package com.semester3.davines.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSeriesRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
