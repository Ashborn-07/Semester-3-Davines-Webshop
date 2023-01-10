package com.semester3.davines.domain.models;

import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private Set<String> roles;
}
