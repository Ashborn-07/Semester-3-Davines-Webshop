package com.semester3.davines.domain.requests;

import lombok.*;

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

    @NotBlank
    private String image;
}
