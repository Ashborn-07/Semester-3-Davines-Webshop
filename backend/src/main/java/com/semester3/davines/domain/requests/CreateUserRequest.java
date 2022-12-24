package com.semester3.davines.domain.requests;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Length(max = 50)
    private String password;
}
