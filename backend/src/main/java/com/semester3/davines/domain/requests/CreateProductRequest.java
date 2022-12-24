package com.semester3.davines.domain.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String type;

    @NotNull
    private Double price;

    @NotNull
    private Long quantity;

    @NotNull
    private String image;

    @NotNull
    private Long seriesId;
}
