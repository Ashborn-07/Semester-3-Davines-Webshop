package com.semester3.davines.domain.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String name;
}
