package com.semester3.davines.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    private Long id;
    private String name;
    private String description;
    private String image;
}
