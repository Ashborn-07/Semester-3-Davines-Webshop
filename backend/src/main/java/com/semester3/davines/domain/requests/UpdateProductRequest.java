package com.semester3.davines.domain.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private Long id;

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
